package org.mag.mow.model;

import org.mag.mow.enums.Orientation;

public class Tondeuse {

    private Orientation orientation;
    private Position position;

    public Tondeuse() {}

    public Tondeuse(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public void rotateLeft() {
        switch (this.orientation){
            case EST:
            this.orientation = Orientation.NORTH;
                break;
            case WEST:
            this.orientation = Orientation.SOUTH;
                break;
            case NORTH:
             this.orientation = Orientation.WEST;
                break;
            case SOUTH:
            this.orientation = Orientation.EST;
                break;
                default:
                    System.out.println("Orientation not exists");
        }
    }

    public void rotateRight() {
        switch (this.orientation){
            case EST:
                this.orientation = Orientation.SOUTH;
                break;
            case WEST:
                this.orientation = Orientation.NORTH;
                break;
            case NORTH:
                this.orientation = Orientation.EST;
                break;
            case SOUTH:
                this.orientation = Orientation.WEST;
                break;
            default:
                System.out.println("Orientation not exists");
        }
    }

    public void advance(Pelouse pelouse) {
        switch (this.orientation){
            case WEST:
                int abscisseWest = this.position.getX() + 1;
                checkPositionMaxPelouse(abscisseWest, pelouse.getLongeur());
                this.position.setX(abscisseWest);
                break;
            case EST:
                int abscisseEst = this.position.getX() - 1;

                checkPositionMinPelouse(abscisseEst<0);

                this.position.setX(abscisseEst);
                break;
            case NORTH:
                int ordonneNord = this.position.getY() + 1;
                checkPositionMaxPelouse(ordonneNord, pelouse.getLargeur());
                this.position.setY(ordonneNord);
                break;
            case SOUTH:
                int ordonneeSud = this.position.getY() - 1;
                checkPositionMinPelouse(ordonneeSud < 0);
                this.position.setY(ordonneeSud);
                break;
                default:
                    System.out.println("No instruction for advance");
        }


    }

    private void checkPositionMinPelouse(boolean b) {
        if (b) {
            throw new RuntimeException("Tondeuse sort de la pelouse");
        }
    }

    private void checkPositionMaxPelouse(int positionAxe, int longeur) {
        checkPositionMinPelouse(positionAxe > longeur);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
