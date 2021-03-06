package exercises.lifts;

import exercises.lifts.classes.ButtonController;
import exercises.lifts.classes.Const;
import exercises.lifts.classes.Lift;

/**
 * Created by stanislav.matukevich on 14.05.2019.
 */
public class LiftController implements Runnable{

    public Lift[] lift;
    public ButtonController buttonController;

    public LiftController() {
        lift = new Lift[2];
        buttonController = new ButtonController(Const.floorNumber);
        lift[0] = new Lift("Passenger elevator", Const.beginFloor, Const.timeChangeFloor, Const.timeStop, buttonController);
        lift[1] = new Lift("Cargo elevator", Const.beginFloor, Const.timeChangeFloor, Const.timeStop, buttonController);
    }

    public void run () {
        try {
            while (true) {
                startLiftsLogic();
                Thread.sleep(100);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startLiftsLogic (){
        int cl1But = closestButton(lift[0]);
        int cl2But = closestButton(lift[1]);

        if (lift[0].destination == 0 && //отправляемся в closestButton(lift[0])
                cl1But != 0){
            System.out.println(lift[0].name + " went to the floor number " + Math.abs(cl1But));
            lift[0].setDestination(Math.abs(cl1But));
            lift[0].nextDestination = cl1But > 0 ? Const.floorNumber : 1;
        }

        if (lift[0].destination != 0 && //отправляемся в closestButton(lift[1]) вверх
                lift[1].destination == 0 &&
                cl2But>0 &&
                lift[0].getCurrentFloor()> cl2But &&
                lift[0].getCurrentFloor()-lift[0].destination<0) {
            System.out.println(lift[1].name + " went to the floor number " + Math.abs(cl2But));
            lift[1].destination = Math.abs(cl2But);
            lift[1].nextDestination = Const.floorNumber;
        }

        if (lift[0].destination != 0 && //отправляемся в closestButton(lift[1]) вниз
                lift[1].destination == 0 &&
                cl2But<0 &&
                lift[0].getCurrentFloor()< Math.abs(cl2But) &&
                lift[0].getCurrentFloor()-lift[0].destination>0) {
            System.out.println(lift[1].name + " went to the floor number " + Math.abs(cl2But));
            lift[1].destination = Math.abs(cl2But);
            lift[1].nextDestination = 1;
        }
    }

    public int closestButton(Lift lift){ //return floornumber and direction
        int result = 0;
        int distance = buttonController.floorNumber;
        for (int i = 0; i < buttonController.floorNumber; i++) {
            if (buttonController.buttons[i][0] == 1 || buttonController.buttons[i][1] == 1) {

                if (Math.abs(lift.getCurrentFloor()-i-1) < distance) {
                    distance = Math.abs(lift.getCurrentFloor()-i-1);
                    result = i+1;
                    if (buttonController.buttons[i][1] == 1) {
                        result *=-1;
                    }
                }
            }
        }
            return result;
    }

    public static void main(String[] args)  {
        LiftController liftController = new LiftController();
        new Thread(liftController).start();
        for (int i = 0; i < liftController.lift.length ; i++) {
            new Thread(liftController.lift[i]).start();
        }
        new Thread(liftController.buttonController).start();
    }
    // request
    // request
    //my code
    //my super code
}