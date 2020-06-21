package co.com.lunchdelivery.fileutilities;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class FileProcessing {

  private ClassLoader classLoader = getClass().getClassLoader();
  private static final String FILE_PATH = "src/test/resources/";

  public String readFile(String filePath) throws URISyntaxException, IOException {
    String fileContent = "";
    try {
      fileContent = Files.readString(Paths.get(getReadingFileURL(filePath).toURI()));
    } catch (IOException e) {
      log.info("An error has occurred reading path: {}", filePath);
      throw e;
    }
    return fileContent;
  }

  public void generateFile(String name, String content) throws IOException {
    Files.write(Paths.get(FILE_PATH + name), content.getBytes());
  }

  private URL getReadingFileURL(String filePath) {
    URL url = classLoader.getResource(filePath);
    return url == null ? classLoader.getResource("") : url;
  }

}
