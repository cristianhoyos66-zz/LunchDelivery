package co.com.lunchdelivery.drone;

import co.com.lunchdelivery.exceptions.MovementException;
import lombok.*;

import java.util.Map;
import java.util.concurrent.Callable;

@Data
public class Drone {

  private Integer id = 0;
  private Integer x = 0;
  private Integer y = 0;
  private DroneDirectionsEnum enumDirection = DroneDirectionsEnum.N;
  private DroneEnum enumCapacity = DroneEnum.CAPACITY;
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<DroneDirectionsEnum, Callable<Integer>> forwardMovements = Map.of(
      DroneDirectionsEnum.N, () -> this.y++,
      DroneDirectionsEnum.W, () -> this.x--,
      DroneDirectionsEnum.S, () -> this.y--,
      DroneDirectionsEnum.E, () -> this.x++
  );
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<DroneDirectionsEnum, Callable<DroneDirectionsEnum>> leftMovements = Map.of(
      DroneDirectionsEnum.N, () -> this.enumDirection = DroneDirectionsEnum.W,
      DroneDirectionsEnum.W, () -> this.enumDirection = DroneDirectionsEnum.S,
      DroneDirectionsEnum.S, () -> this.enumDirection = DroneDirectionsEnum.E,
      DroneDirectionsEnum.E, () -> this.enumDirection = DroneDirectionsEnum.N
  );
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Map<DroneDirectionsEnum, Callable<DroneDirectionsEnum>> rightMovements = Map.of(
      DroneDirectionsEnum.N, () -> this.enumDirection = DroneDirectionsEnum.E,
      DroneDirectionsEnum.E, () -> this.enumDirection = DroneDirectionsEnum.S,
      DroneDirectionsEnum.S, () -> this.enumDirection = DroneDirectionsEnum.W,
      DroneDirectionsEnum.W, () -> this.enumDirection = DroneDirectionsEnum.N
  );

  public void moveDrone(String movements) throws MovementException {
    for (int i = 0; i < movements.length(); i++) {
      char order = movements.charAt(i);
      if (order == DroneMovementsEnum.A.getMovement()) {
        moveForward();
      } else if (order == DroneMovementsEnum.I.getMovement()) {
        turnToLeft();
      } else {
        turnToRight();
      }
    }
  }

  private void moveForward() throws MovementException{
    try {
      forwardMovements.get(enumDirection).call();
    } catch (Exception e) {
      throw new MovementException(e.getMessage());
    }
  }

  private void turnToLeft() throws MovementException {
    try {
      leftMovements.get(enumDirection).call();
    } catch (Exception e) {
      throw new MovementException(e.getMessage());
    }
  }

  private void turnToRight() throws MovementException {
    try {
      rightMovements.get(enumDirection).call();
    } catch (Exception e) {
      throw new MovementException(e.getMessage());
    }
  }


}
