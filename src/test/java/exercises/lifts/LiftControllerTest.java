package exercises.lifts;

import exercises.lifts.classes.Const;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by stanislav.matukevich on 15.05.2019.
 */

public class LiftControllerTest {

    LiftController liftController = new LiftController();

    @Test(groups="smoke")
    public void startPosition() {
        Assert.assertEquals(liftController.lift[0].getCurrentFloor(), Const.beginFloor);
    }

    @Test(groups="smoke",dependsOnMethods={"startPosition"})
    public void moveUpLift() {
        liftController.lift[0].moveUp();
        Assert.assertEquals(liftController.lift[0].getCurrentFloor(), Const.beginFloor+1);
    }

}
