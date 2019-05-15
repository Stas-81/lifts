package exercises.lifts;

import exercises.lifts.classes.ButtonListener;
import exercises.lifts.classes.Const;
import exercises.lifts.classes.Lift;

/**
 * Created by stanislav.matukevich on 14.05.2019.
 */
public class LiftController implements Runnable{

    public Lift[] lift;


    public LiftController() {
        lift = new Lift[2];
        lift[0] = new Lift("Cargo elevator", Const.beginFloor, Const.timeChangeFloor, Const.timeStop);
        lift[1] = new Lift("Passenger elevator", Const.beginFloor, Const.timeChangeFloor, Const.timeStop);
    }

    public void run () {
        try {
            Thread.sleep(1000);
            System.out.println("Hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        LiftController liftController = new LiftController();
        new Thread(liftController).start();
        for (int i = 0; i <liftController.lift.length ; i++) {
            new Thread(liftController.lift[i]).start();
        }
        ButtonListener listener = new ButtonListener(Const.floorNumber);
        new Thread(listener).start();
    }
}
