package co.com.lunchdelivery.drone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DroneEnum {
  CAPACITY(3);
  private Integer capacity;
}
