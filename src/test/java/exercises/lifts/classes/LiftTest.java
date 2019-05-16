package exercises.lifts.classes;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertTrue;

/**
 * Created by stanislav.matukevich on 16.05.2019.
 */
public class LiftTest {

    @Test(groups="Regression")
    public void getCurrentFloorTest (){
        ButtonController buttonController = new ButtonController(2);
        Lift lift = new Lift("Passenger elevator", 2, Const.timeChangeFloor, Const.timeStop, buttonController);
        Assert.assertEquals(lift.getCurrentFloor(),2);
    }

    @Test(groups="Regression")
    public void movementLogicTestUp () throws Exception {
        ButtonController buttonController = new ButtonController(Const.floorNumber);
        Lift lift = new Lift("Passenger elevator", 1, Const.timeChangeFloor, Const.timeStop, buttonController);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        lift.destination = Const.floorNumber;
        buttonController.pushButton("U1");
        lift.movementLogic();

        outputStream.flush();
        String allWrittenLines = new String(outputStream.toByteArray());
        assertTrue(allWrittenLines.contains("to get up passengers"));
    }

    @Test(groups="Regression")
    public void movementLogicTestDown () throws Exception {
        ButtonController buttonController = new ButtonController(Const.floorNumber);
        Lift lift = new Lift("Passenger elevator", Const.floorNumber, Const.timeChangeFloor, Const.timeStop, buttonController);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        lift.destination = 1;
        buttonController.pushButton("D"+Const.floorNumber);
        lift.movementLogic();

        outputStream.flush();
        String allWrittenLines = new String(outputStream.toByteArray());
        assertTrue(allWrittenLines.contains("to get up passengers"));
    }

    @Test(groups="Regression")
    public void stopTest () throws Exception{
        ButtonController buttonController = new ButtonController(2);
        Lift lift = new Lift("Passenger elevator", 1, Const.timeChangeFloor, Const.timeStop, buttonController);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        lift.stop();

        outputStream.flush();
        String allWrittenLines = new String(outputStream.toByteArray());
        assertTrue(allWrittenLines.contains("stopped on the floor №"));
    }

    @Test(groups="Regression")
    public void checkDestinationTest (){
        ButtonController buttonController = new ButtonController(Const.floorNumber);
        Lift lift = new Lift("Passenger elevator", 1, Const.timeChangeFloor, Const.timeStop, buttonController);
        lift.setDestination(lift.getCurrentFloor());
        lift.nextDestination = Const.floorNumber;
        lift.checkDestination();

        Assert.assertEquals(lift.destination,Const.floorNumber);
    }
}