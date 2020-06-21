package co.com.lunchdelivery.delivery;

import co.com.lunchdelivery.exceptions.MovementException;
import co.com.lunchdelivery.fileutilities.FileProcessing;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DeliveryTest {

  private Delivery deliver;
  private FileProcessing fileProcessing;

  @Before
  public void setup() {
    deliver = new Delivery();
    fileProcessing = new FileProcessing();
  }

  @Test
  public void deliverLunchesShouldGenerateDeliveriesFile() throws URISyntaxException, MovementException, IOException, InterruptedException {
    deliver.deliverLunches("in.txt");
    deliverLunchesAssert();
  }

  private void deliverLunchesAssert() {
    CompletableFuture.runAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
        String[] deliveries = fileProcessing.readFile("out0.txt").split("\n");
        Assert.assertEquals("(-2, 4) West direction ", deliveries[0]);
        Assert.assertEquals("(-1, 3) South direction ", deliveries[1]);
        Assert.assertEquals("(0, 0) West direction ", deliveries[2]);

      } catch (URISyntaxException e) {
        log.error("There's an error with path URI file");
      } catch (IOException e) {
        log.error("There's an error ");
      } catch (InterruptedException e) {
        log.error("Thread cannot be stopped");
      }
    });
  }

}
