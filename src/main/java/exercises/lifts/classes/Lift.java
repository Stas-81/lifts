package exercises.lifts.classes;

/**
 * Created by stanislav.matukevich on 14.05.2019.
 */
public class Lift implements Runnable {

    public void run () {
        try {
            Thread.sleep(500);
            System.out.println("Hello from "+name);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private String name;

    private int currentFloor;

    private int timeChangeFloor;
    private int timeStop;

    private Integer destination;
    private Integer nextDestination;

    public Lift(String name, int currentFloor, int timeChangeFloor, int timeStop) {
        this.name = name;
        this.currentFloor = currentFloor;
        this.timeChangeFloor = timeChangeFloor;
        this.timeStop = timeStop;
        this.destination = currentFloor;
        this.nextDestination = currentFloor;
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
        } catch (InterruptedException e) {
            //ignore
        }
    }

    public void moveDown() {
        try {
            Thread.sleep(timeChangeFloor);
            currentFloor--;
        } catch (InterruptedException e) {
            //ignore
        }
    }

    public void stop() {
        try {
            Thread.sleep(timeStop);
        } catch (InterruptedException e) {
            //ignore
        }
    }

}
