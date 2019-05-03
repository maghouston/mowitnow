package org.mag.mow;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mag.mow.enums.Orientation;
import org.mag.mow.model.Pelouse;
import org.mag.mow.model.Position;
import org.mag.mow.model.Tondeuse;
import org.mag.mow.service.TondeuseService;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldRotateLeftWhenOrientationNorth() {

        //When
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setOrientation(Orientation.NORTH);
        //
        tondeuse.rotateLeft();
        //Then
        assertTrue( tondeuse.getOrientation() == Orientation.WEST );
    }

    @Test
    public void shouldRotateLeftWhenOrientationWest() {

        //When
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setOrientation(Orientation.WEST);
        //
        tondeuse.rotateLeft();
        //Then
        assertTrue( tondeuse.getOrientation() == Orientation.SOUTH );
    }

    @Test
    public void shouldRotateLeftWhenOrientationSouth() {

        //When
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setOrientation(Orientation.SOUTH);
        //
        tondeuse.rotateLeft();
        //Then
        assertTrue( tondeuse.getOrientation() == Orientation.EST );
    }

    @Test
    public void shouldRotateLeftWhenOrientationEast() {

        //When
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setOrientation(Orientation.EST);
        //
        tondeuse.rotateLeft();
        //Then
        assertTrue( tondeuse.getOrientation() == Orientation.NORTH );
    }

    @Test
    public void shouldRotateRightWhenOrientationEast() {

        //When
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setOrientation(Orientation.EST);
        //
        tondeuse.rotateRight();
        //Then
        assertTrue( tondeuse.getOrientation() == Orientation.SOUTH );
    }

    @Test
    public void shouldRotateRightWhenOrientationWEST() {

        //When
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setOrientation(Orientation.WEST);
        //
        tondeuse.rotateRight();
        //Then
        assertTrue( tondeuse.getOrientation() == Orientation.NORTH );
    }

    @Test
    public void shouldRotateRightWhenOrientationNORTG() {

        //When
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setOrientation(Orientation.NORTH);
        //
        tondeuse.rotateRight();
        //Then
        assertTrue( tondeuse.getOrientation() == Orientation.EST );
    }

    @Test
    public void shouldRotateRightWhenOrientationSOUTH() {

        //When
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setOrientation(Orientation.SOUTH);
        //
        tondeuse.rotateRight();
        //Then
        assertTrue( tondeuse.getOrientation() == Orientation.WEST );
    }

    @Test
    public void shouldgoaheadOnSameOrientation() {

        //When
        Tondeuse tondeuse = new Tondeuse();
        tondeuse.setOrientation(Orientation.EST);
        //
        tondeuse.setPosition(new Position(2,3));
        tondeuse.advance(new Pelouse(10,10));
        //Then
        assertTrue( tondeuse.getOrientation() == Orientation.EST );
    }

    @Test
    public void shouldgoaheadWhenInsidePelouse() {

        //When
        Tondeuse tondeuse = new Tondeuse(new Position(2,3), Orientation.WEST);
        Pelouse pelouse = new Pelouse(15,10);
        //
        tondeuse.advance(pelouse);
        //Then
        assertTrue( tondeuse.getPosition().getX()<pelouse.getLongeur() && tondeuse.getPosition().getY()<pelouse.getLargeur());
    }

    @Test
    public void shouldgoaheadWhenInsidePelouseAdvanceOnCellSameOrientationWEST() {

        //When
        Tondeuse tondeuse = new Tondeuse(new Position(2,3), Orientation.WEST);
        Pelouse pelouse = new Pelouse(15,10);
        //
        tondeuse.advance(pelouse);
        //Then
        assertTrue( tondeuse.getPosition().getX()==3 );
        assertTrue( tondeuse.getOrientation() == Orientation.WEST );
    }

    @Test
    public void shouldgoaheadWhenInsidePelouseAdvanceOnCellSameOrientationEST() {

        //When
        Tondeuse tondeuse = new Tondeuse(new Position(2,3), Orientation.EST);
        Pelouse pelouse = new Pelouse(15,10);
        //
        tondeuse.advance(pelouse);
        //Then
        assertTrue( tondeuse.getPosition().getX()==1 );
        assertTrue( tondeuse.getOrientation() == Orientation.EST );
    }

    @Test
    public void shouldgoaheadWhenInsidePelouseAdvanceOnCellSameOrientationNord() {

        //When
        Tondeuse tondeuse = new Tondeuse(new Position(2,3), Orientation.NORTH);
        Pelouse pelouse = new Pelouse(15,10);
        //
        tondeuse.advance(pelouse);
        //Then
        assertTrue( tondeuse.getPosition().getY()==4 );
        assertTrue( tondeuse.getOrientation() == Orientation.NORTH );
    }

    @Test
    public void shouldgoaheadWhenInsidePelouseAdvanceOnCellSameOrientationSOUTH() {

        //When
        Tondeuse tondeuse = new Tondeuse(new Position(2,3), Orientation.SOUTH);
        Pelouse pelouse = new Pelouse(15,10);
        //
        tondeuse.advance(pelouse);
        //Then
        assertTrue( tondeuse.getPosition().getY()==2 );
        assertTrue( tondeuse.getOrientation() == Orientation.SOUTH );
    }

    @Test(expected = RuntimeException.class)
    public void shouldgoaheadWhenInsidePelouseAdvanceOnCellSameOrientationSOUTHSortiePelouse() {

        //When
        Tondeuse tondeuse = new Tondeuse(new Position(0,0), Orientation.SOUTH);
        Pelouse pelouse = new Pelouse(15,10);
        //
        tondeuse.advance(pelouse);
        //Then
        assertTrue( tondeuse.getPosition().getY()==2 );
        assertTrue( tondeuse.getOrientation() == Orientation.SOUTH );
    }

    @Test
    public void testService() {

        //When
        TondeuseService tondeuseService = new TondeuseService();
        Tondeuse tondeuse = new Tondeuse(new Position(0,0), Orientation.SOUTH);
        Pelouse pelouse = new Pelouse(15,10);
        //
        tondeuseService.tondrePelouse("D:\\tmp\\test.txt", tondeuse);
        //Then
        assertTrue( tondeuse.getPosition().getY()==3 );
        assertTrue( tondeuse.getPosition().getX()==1 );
        assertTrue( tondeuse.getOrientation() == Orientation.NORTH );
    }

}
