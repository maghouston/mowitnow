package org.mag.mow.service;

import org.mag.mow.enums.InstructionEnum;
import org.mag.mow.enums.Orientation;
import org.mag.mow.model.Pelouse;
import org.mag.mow.model.Position;
import org.mag.mow.model.Tondeuse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TondeuseService {

    public void tondrePelouse(String pathInstruction, Tondeuse tondeuse)  {
        List<String> instructions = getInstruction(pathInstruction);
        int longueurPelouse = Integer.parseInt(instructions.get(0).split(" ")[0]);
        int largueurPelouse = Integer.parseInt(instructions.get(0).split(" ")[1]);
        Pelouse pelouse = new Pelouse(longueurPelouse, largueurPelouse);

        int absciseTondeuse = Integer.parseInt(instructions.get(1).split(" ")[0]);
        int ordonneeTondeuse = Integer.parseInt(instructions.get(1).split(" ")[1]);
        String orientation = instructions.get(1).split(" ")[2];

        Position position = new Position(absciseTondeuse,ordonneeTondeuse);
        tondeuse.setPosition(position);
        tondeuse.setOrientation(Orientation.getOrientation(orientation));

        String [] instructionDirection = instructions.get(2).split(" ");
        System.out.println("Position initiale "+tondeuse.getPosition().getX()+","+tondeuse.getPosition().getY()+","+tondeuse.getOrientation());
        for(String s : instructionDirection) {
            if(s.equals(InstructionEnum.A.name())) {
                tondeuse.advance(pelouse);
            }else if(s.equals(InstructionEnum.G.name())) {
                tondeuse.rotateLeft();
            }else{
                tondeuse.rotateRight();
            }
            System.out.println("Position intermediaire "+tondeuse.getPosition().getX()+","+tondeuse.getPosition().getY()+","+tondeuse.getOrientation());
        }
        System.out.println("Hello je suis à la position "+tondeuse.getPosition().getX()+","+tondeuse.getPosition().getY()+","+tondeuse.getOrientation());
    }


    private List<String> getInstruction(String pathInstruction){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathInstruction));
            String line = "";
            List<String> content = new ArrayList<>();
            while((line=reader.readLine())!=null ){
                content.add(line);
            }
            return content;
        }catch (IOException e) {
            throw new RuntimeException("File not ok");
        }

    }
}
