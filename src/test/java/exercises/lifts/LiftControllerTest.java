package exercises.lifts;

import exercises.lifts.classes.Const;
import exercises.lifts.classes.Lift;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertTrue;

/**
 * Created by stanislav.matukevich on 15.05.2019.
 */

public class LiftControllerTest {

    LiftController liftController = new LiftController();

    @Test(groups="smoke")
    public void closestButtonCheckUp(){
        LiftController liftController = new LiftController();
        liftController.buttonController.pushButton("U2");
        Assert.assertEquals(liftController.closestButton(liftController.lift[0]),2);
    }

    @Test(groups="smoke")
    public void closestButtonCheckDown(){
        LiftController liftController = new LiftController();
        liftController.buttonController.pushButton("D7");
        Assert.assertEquals(liftController.closestButton(liftController.lift[0]),-7);
    }

    @Test(groups="smoke")
    public void startLiftsLogicCheckFirstLift () throws Exception{
        LiftController liftController = new LiftController();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        liftController.buttonController.buttons[1][0]=1;
        liftController.startLiftsLogic();

        outputStream.flush();
        String allWrittenLines = new String(outputStream.toByteArray());
        assertTrue(allWrittenLines.contains("Passenger elevator went to the floor № 2"));
    }

    @Test(groups="smoke")
    public void startLiftsLogicCheckSecondLift () throws Exception{
        LiftController liftController = new LiftController();
        liftController.lift[0] = new Lift("Passenger elevator", 6, Const.timeChangeFloor, Const.timeStop, liftController.buttonController);
        liftController.lift[1] = new Lift("Cargo elevator", 1, Const.timeChangeFloor, Const.timeStop, liftController.buttonController);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        liftController.buttonController.buttons[7][0]=1;
        liftController.buttonController.buttons[1][0]=1;

        liftController.startLiftsLogic();

        outputStream.flush();
        String allWrittenLines = new String(outputStream.toByteArray());
        //Assert.assertEquals(allWrittenLines, "");
        assertTrue(allWrittenLines.contains("Cargo elevator went to the floor № 2"));
    }

    @Test(groups="smoke") //2do -> send to liftTestClass
    public void startPosition() {
        Assert.assertEquals(liftController.lift[0].getCurrentFloor(), Const.beginFloor);
    }

    @Test(groups="smoke",dependsOnMethods={"startPosition"})
    public void moveUpLift() {
        liftController.lift[0].moveUp();
        Assert.assertEquals(liftController.lift[0].getCurrentFloor(), Const.beginFloor+1);
    }

    @Test(groups="smoke",dependsOnMethods={"moveUpLift"})
    public void moveDownLift() {
        liftController.lift[0].moveDown();
        Assert.assertEquals(liftController.lift[0].getCurrentFloor(), Const.beginFloor);
    }

}
