package exercises.lifts.classes;

/**
 * Created by stanislav.matukevich on 14.05.2019.
 */
public class Lift implements Runnable {

    public String name;

    private int currentFloor;

    private int timeChangeFloor;
    private int timeStop;

    public int destination;
    public int nextDestination;

    private ButtonController buttonController;

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public Lift(String name, int currentFloor, int timeChangeFloor, int timeStop, ButtonController buttonController) {
        this.name = name;
        this.currentFloor = currentFloor;
        this.timeChangeFloor = timeChangeFloor;
        this.timeStop = timeStop;
        this.destination = 0;
        this.nextDestination = 0;
        this.buttonController = buttonController;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void run () {
        try {
            while (true) {
                if (currentFloor < destination) {
                    if (destination == Const.floorNumber && buttonController.buttons[currentFloor-1][0]==1){ //забираем попутчиков
                        //System.out.println(name+" stoped on "+currentFloor+" to get up passengers");
                        //stop();
                        //buttonController.releaseButton("U"+currentFloor);
                    }
                    moveUp();
                }
                if (destination > 0 && currentFloor > destination) {
                    if (destination == 1 && buttonController.buttons[currentFloor-1][0]==1){ //забираем попутчиков
                        //System.out.println(name+" stoped on "+currentFloor+" to get up passengers");
                        //stop();
                        //buttonController.releaseButton("D"+currentFloor);
                    }
                    moveDown();
                }

                checkDestination();
                Thread.sleep(100);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void moveUp() {
        try {
            currentFloor++;
            Thread.sleep(timeChangeFloor);
            checkDestination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveDown() {
        try {
            currentFloor--;
            Thread.sleep(timeChangeFloor);
            checkDestination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            Thread.sleep(timeStop);
            System.out.println(name +" stopped on the floor №"+currentFloor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkDestination (){
        if (currentFloor == destination) {
            destination = 0;
            stop();
            if (nextDestination >0) {
                if (nextDestination > currentFloor) {
                    buttonController.releaseButton("U"+currentFloor);
                    //System.out.println("release");
                } else {
                    buttonController.releaseButton("D"+currentFloor);
                }
                destination = nextDestination;
                nextDestination = 0;
            }
        }
    }
}