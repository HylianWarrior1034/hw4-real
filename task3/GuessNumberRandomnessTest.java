import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class GuessNumberRandomnessTest {
  @Test
  public void testGuessNumberRandomness() {

    int[] dist = new int[100];

    for (int i = 0; i<30000; i++) {
      byte[] data = "-1\n-1\n-1\n-1\n-1\n".getBytes(StandardCharsets.UTF_8);
      BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(data));
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      PrintStream out = new PrintStream(output);

      System.setIn(in);
      System.setOut(out);

      GuessNumber.guessingNumberGame(new Random());
      String string = output.toString();
      String[] strList = string.split(" ");
      int num = Integer.parseInt(strList[strList.length-1].replaceAll("\r\n", ""));
      dist[num-1]++;
    }

    System.setIn(System.in);
    System.setOut(System.out);

    int max = Arrays.stream(dist).max().getAsInt();
    int min = Arrays.stream(dist).min().getAsInt();

    if (min * 1.5 > max && max / 1.5 < min) {
      return;
    }

    fail("Frequencies are not random");
  }
}
