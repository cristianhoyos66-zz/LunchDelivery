package co.com.lunchdelivery.fileutilities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class FileProcessingTest {

  private FileProcessing fileProcessing;

  @Before
  public void setup() {
    fileProcessing = new FileProcessing();
  }

  @Test
  public void readFileWhenPathIsCorrectShouldReturnFileContent() throws URISyntaxException, IOException {
    String fileContent = fileProcessing.readFile("in.txt");

    Assert.assertNotEquals("", fileContent);
  }

  @Test(expected = IOException.class)
  public void readFileWhenPathIsNotCorrectShouldThrowException() throws URISyntaxException, IOException {
    fileProcessing.readFile("inBad.txt");
  }

}
