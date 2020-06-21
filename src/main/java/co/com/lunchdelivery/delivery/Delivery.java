package co.com.lunchdelivery.delivery;

import co.com.lunchdelivery.drone.Drone;
import co.com.lunchdelivery.drone.DroneEnum;
import co.com.lunchdelivery.exceptions.MovementException;
import co.com.lunchdelivery.fileutilities.FileProcessing;
import co.com.lunchdelivery.genericutilities.GenericBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class Delivery {

  private FileProcessing fileProcessing = new FileProcessing();

  public void deliverLunches(String inputFile) throws IOException, URISyntaxException, MovementException {
    String[] orders = fileProcessing.readFile(inputFile).split("\n");
    Drone drone = GenericBuilder.of(Drone::new).build();
    deliverOrder(drone, 0, orders, "");
  }

  private void deliverOrder(Drone drone, int startPosition, String[] orders, String orderReport) throws MovementException, IOException {
    for (int i = 0; i < orders.length; i++) {
      drone.moveDrone(orders[i]);
      orderReport = addOrderReport(orderReport, drone);
      if (i == startPosition + DroneEnum.CAPACITY.getCapacity() - 1 || i == orders.length - 1) {
        finishDroneDelivery(orderReport, drone.getId());
        drone = GenericBuilder.of(Drone::new).with(Drone::setId, drone.getId() + 1).build();
        startPosition = i + 1;
        orderReport = "";
      }
    }
  }

  private String addOrderReport(String orderReport, Drone drone) {
    return orderReport.concat(String.format("(%d, %d) %s direction %n", drone.getX(), drone.getY(), drone.getEnumDirection().getDirection()));
  }

  private void finishDroneDelivery(String content, Integer droneId) throws IOException {
    fileProcessing.generateFile("out" + droneId + ".txt", content);
  }

}
