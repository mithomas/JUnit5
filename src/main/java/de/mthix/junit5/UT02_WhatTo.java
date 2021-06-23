package de.mthix.junit5;

import java.util.Collections;
import java.util.List;

import static de.mthix.junit5.UT02_WhatTo.WeightLevel.*;
import static java.util.Arrays.asList;

class UT02_WhatTo {

  public enum WeightLevel {
    LIGHT,
    MEDIUM,
    HEAVY
  }

  private int key   = 7;
  private int value = 54;

  public UT02_WhatTo(int key, int value) {
    this.key = key;
    this.value = value;
  }

  public int getKey() {
    return key;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public WeightLevel getWeight() {
    return calculateWeightLevel(value);
  }

  public static WeightLevel calculateWeightLevel(int weight) {
    if (weight < 0) {
      throw new IllegalArgumentException("Wrong!");
    } else if (weight > 100) {
      return HEAVY;
    } else if (weight > 10) {
      return MEDIUM;
    } else {
      return LIGHT;
    }
  }

  public static List<Integer> reverseNumbers(Integer... numbers) {
    List<Integer> l = asList(numbers);
    Collections.reverse(l);
    return l;
  }

  public static List<Integer> sortNumbers(Integer... numbers) {
    List<Integer> l = asList(numbers);
    Collections.sort(l);
    return l;
  }
}
