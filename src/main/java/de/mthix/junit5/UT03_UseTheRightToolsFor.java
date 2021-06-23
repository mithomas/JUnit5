package de.mthix.junit5;

import static de.mthix.junit5.UT03_UseTheRightToolsFor.Size.*;

class UT03_UseTheRightToolsFor {

  public enum Size {
    SMALL,
    MEDIUM,
    LARGE
  }

  public interface Actor {

    Integer doSomething();
    void doSomethingElse();
    Integer getStatus();
  }


  private int     actCounter;
  private boolean acted;
  private Actor   actor;

  public Integer act() {
    acted = true;
    actCounter++;
    return actor.doSomething();
  }

  public static Size convertSizeCode(String code) {
    switch (String.valueOf(code)) {
      case "s":
        return SMALL;
      case "m":
        return MEDIUM;
      case "l":
        return LARGE;
      default:
        throw new IllegalArgumentException("Wrong code: " + code);
    }
  }
}
