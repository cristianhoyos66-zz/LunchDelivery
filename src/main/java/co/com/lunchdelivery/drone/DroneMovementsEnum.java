package co.com.lunchdelivery.drone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DroneMovementsEnum {
  A('A'),
  I('I'),
  D('D');
  private char movement;
}
