package co.com.lunchdelivery.drone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DroneDirectionsEnum {
  N("North"),
  E("East"),
  W("West"),
  S("South");
  private String direction;
}
