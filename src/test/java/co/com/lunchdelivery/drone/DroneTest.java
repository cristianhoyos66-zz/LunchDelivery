package co.com.lunchdelivery.drone;

import co.com.lunchdelivery.exceptions.MovementException;
import co.com.lunchdelivery.genericutilities.GenericBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DroneTest {

  private Drone drone;

  @Before
  public void setup() {
    drone = GenericBuilder.of(Drone::new).build();
  }

  @Test
  public void moveDroneWhenReceiveOrdersShouldMoveDrone() throws MovementException {
    drone.moveDrone("AAAAIAA");
    assertEquals(-2, drone.getX().intValue());
    assertEquals(4, drone.getY().intValue());
    assertSame(DroneDirectionsEnum.W, drone.getEnumDirection());
  }

}
