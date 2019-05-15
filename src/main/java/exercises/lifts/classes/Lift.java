package exercises.lifts.classes;

/**
 * Created by stanislav.matukevich on 14.05.2019.
 */
public class Lift implements Runnable {

    public void run () {
        try {
            while (true) {
                if (currentFloor < destination) {
                    moveUp();
                }
                if (destination > 0 && currentFloor > destination) {
                    moveDown();
                }
                checkDestination();
                Thread.sleep(100);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private String name;

    private int currentFloor;

    private int timeChangeFloor;
    private int timeStop;

    public int destination;
    public int nextDestination;

    private ButtonListener buttonListener;

    public void setDestination(int destination) {
        //stop();
        this.destination = destination;
    }

    public Lift(String name, int currentFloor, int timeChangeFloor, int timeStop, ButtonListener buttonListener) {
        this.name = name;
        this.currentFloor = currentFloor;
        this.timeChangeFloor = timeChangeFloor;
        this.timeStop = timeStop;
        this.destination = 0;
        this.nextDestination = 0;
        this.buttonListener = buttonListener;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void moveUp() {
        try {
            Thread.sleep(timeChangeFloor);
            currentFloor++;
            checkDestination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveDown() {
        try {
            Thread.sleep(timeChangeFloor);
            currentFloor--;
            checkDestination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            Thread.sleep(timeStop);
            destination = 0;
            System.out.println(name +" остановился на этаже №"+currentFloor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkDestination (){
        if (currentFloor == destination) {
            stop();
            if (nextDestination >0) {
                if (nextDestination > currentFloor) {
                    buttonListener.releaseButton("U"+currentFloor);
                    System.out.println("release");
                } else {
                    buttonListener.releaseButton("D"+currentFloor);
                }
                destination = nextDestination;
                nextDestination = 0;
            }
        }
    }
}