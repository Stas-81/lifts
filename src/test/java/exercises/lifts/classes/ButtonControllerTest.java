package exercises.lifts.classes;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.testng.Assert.assertTrue;

/**
 * Created by stanislav.matukevich on 16.05.2019.
 */
public class ButtonControllerTest {

    @Test(groups="Regression")
    public void parseUserInputTest () throws Exception{
        ButtonController buttonController = new ButtonController(Const.floorNumber);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        buttonController.parseUserInput(new Scanner("U0"));

        outputStream.flush();
        String allWrittenLines = new String(outputStream.toByteArray());
        assertTrue(allWrittenLines.contains("Wrong input command"));

    }
}
