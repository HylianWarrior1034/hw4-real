import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterTest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GuessNumberIOTest {

  @AfterTest
  public void resetIO() {
    System.setIn(System.in);
    System.setOut(System.out);
  }

  @Test
  public void GuessNumberCorrectTest() {
    byte[] data = "61\n".getBytes(StandardCharsets.UTF_8);
    BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(output);

    System.setIn(in);
    System.setOut(out);

    GuessNumber.guessingNumberGame(new Random(0));
    String string = output.toString();
    assertEquals("A number is chosen between 1 to 100. Guess the number within 5 trials.\r\n" +
            "Guess the number: Congratulations! You guessed the number.\r\n", string);
    System.setIn(System.in);
    System.setOut(System.out);
  }

  @Test
  public void GuessNumberIncorrectTestUnder() {
    byte[] data = "60\n60\n60\n60\n60\n".getBytes(StandardCharsets.UTF_8);
    BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(output);

    System.setIn(in);
    System.setOut(out);

    GuessNumber.guessingNumberGame(new Random(0));
    String string = output.toString();
    assertEquals("A number is chosen between 1 to 100. Guess the number within 5 trials.\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "You have exhausted 5 trials.\r\n" +
            "The number was 61\r\n", string);
  }

  /*
  Failure occurs due to the loop not taking in the 5th guess. After "Guess the number," the loop does not take in the
  guess and goes straight to "You have exhausted 5 trials."
   */
  @Test
  public void GuessNumberIncorrectTestOver() {
    byte[] data = "70\n70\n70\n70\n70\n".getBytes(StandardCharsets.UTF_8);
    BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(output);

    System.setIn(in);
    System.setOut(out);

    GuessNumber.guessingNumberGame(new Random(0));
    String string = output.toString();
    assertEquals("A number is chosen between 1 to 100. Guess the number within 5 trials.\r\n" +
            "Guess the number: The number is less than 70\r\n" +
            "Guess the number: The number is less than 70\r\n" +
            "Guess the number: The number is less than 70\r\n" +
            "Guess the number: The number is less than 70\r\n" +
            "Guess the number: The number is less than 70\r\n" +
            "You have exhausted 5 trials.\r\n" +
            "The number was 61\r\n", string);
  }

  @Test
  public void GuessNumberIncorrectNondigit() {
    byte[] data = "hi\nhi\nhi\nhi\nhi\n".getBytes(StandardCharsets.UTF_8);
    BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(output);

    System.setIn(in);
    System.setOut(out);

    try {
      GuessNumber.guessingNumberGame(new Random(0));
    } catch (InputMismatchException e) {
      return;
    }
    fail("Did not catch exception");
  }

  /*
  The test fails because the loop doesn't check if the guess is within 1 to 100. This may be an oversight or intentional
  game design, but I think this is an oversight because the number is between 1 to 100, so the guess should be within
  this range.
   */
  @Test
  public void GuessNumberIncorrectUnder1() {

    byte[] data = "-1\n60\n60\n60\n60\n60\n".getBytes(StandardCharsets.UTF_8);
    BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(output);

    System.setIn(in);
    System.setOut(out);

    GuessNumber.guessingNumberGame(new Random(0));
    String string = output.toString();
    assertEquals("A number is chosen between 1 to 100. Guess the number within 5 trials.\r\n" +
            "Guess the number: Input a number greater than or equal to 1.\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "You have exhausted 5 trials.\r\n" +
            "The number was 61\r\n", string);
  }

  /*
    Same thing as the test above. The test fails because the loop doesn't check if the guess is within 1 to 100. This
    may be an oversight or intentional game design, but I think this is an oversight because the number is between 1 to
    100, so the guess should be within this range.
 */
  @Test
  public void GuessNumberIncorrectOver100() {

    byte[] data = "101\n60\n60\n60\n60\n60\n".getBytes(StandardCharsets.UTF_8);
    BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(output);

    System.setIn(in);
    System.setOut(out);

    GuessNumber.guessingNumberGame(new Random(0));
    String string = output.toString();
    assertEquals("A number is chosen between 1 to 100. Guess the number within 5 trials.\r\n" +
            "Guess the number: Input a number less than or equal to 100.\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "Guess the number: The number is greater than 60\r\n" +
            "You have exhausted 5 trials.\r\n" +
            "The number was 61\r\n", string);
  }
}
