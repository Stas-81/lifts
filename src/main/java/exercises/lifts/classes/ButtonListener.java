package exercises.lifts.classes;

import java.util.Scanner;

public class ButtonListener implements Runnable {

    public int floorNumber;
    private Scanner scanner = new Scanner(System.in);
    public int[][] buttons;

    public ButtonListener (int floorNumber){
        this.floorNumber = floorNumber;
        buttons = new int[floorNumber][2]; //first UP, second DOWN
    }

    public void run() {
        try {
            String inp;
            System.out.println("Push button by command: U1/D2");
            while (true) {
                Thread.sleep(100);
                inp = scanner.nextLine();
                if ("EXIT".equals(inp)){
                    System.out.println("Stop program by user");
                    System.exit(0); //need 2stop all threads before
                } else if (inp.matches("[UD][1-"+ Const.floorNumber +"]") && !"D1".equals(inp) && !("U"+Const.floorNumber).equals(inp))
                {
                    System.out.println("Pressed button: " + inp);
                    pushButton(inp);
                } else {
                    System.out.println("Wrong input command.");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pushButton(String inp){
        buttons[Integer.parseInt(inp.substring(1,2))-1]["U".equals(inp.substring(0,1))?0:1] = 1;
        //System.out.println(Integer.parseInt(inp.substring(1,2))-1);
        //System.out.println("U".equals(inp.substring(0,1))?0:1);
    }

    public void releaseButton(String inp){
        buttons[Integer.parseInt(inp.substring(1,2))-1]["U".equals(inp.substring(0,1))?0:1] = 0;
    }
}