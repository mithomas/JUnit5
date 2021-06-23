package de.mthix.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static de.mthix.junit5.UT02_WhatTo.*;
import static de.mthix.junit5.UT02_WhatTo.WeightLevel.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <h2>What to test</h2>
 *
 * <h3>No simple property getters/setters</h3>
 * <p><strong>What</strong>: avoid testing of simple getters/setters.</p>
 * <p><strong>Why</strong>: testing simple property getters/setters does not add much value and is often done implicitly anyway.</p>
 * <p>Obviously if there is logic in a getter/setter or they return a generated value, testing is required.</p>
 * <br>
 *
 * <h3>Test defaults</h3>
 * <p><strong>What</strong>: check values right after construction of on object.</p>
 * <p><strong>Why</strong>: this makes sure you can rely on presets.</p>
 * <p>Name the relevant class <em>Defaults</em>.</p>
 * <p><strong>Example</strong>: {@link CalculateWeightLevel#light(int)}</p>
 * <br>
 *
 * <h3>Test corner cases</h3>
 * <p><strong>What</strong>: check values at the end of ranges.</p>
 * <p><strong>Why</strong>: this primarily catches off-by-one errors.</p>
 * <p><strong>Example</strong>: {@link CalculateWeightLevel#light(int)}</p>
 * <br>
 *
 * <h3>Test exceptions</h3>
 * <p><strong>What</strong>: test errors and validation code.</p>
 * <p><strong>Why</strong>: negative cases are just as important as positive ones</p>
 * <p><strong>Example</strong>: {@link CalculateWeightLevel#invalid(int)}</p>
 * <br>
 *
 * <h3>Write regression tests per defect</h3>
 * <p><strong>What</strong>: for each defect related to Java code, write one regression test <strong>before</strong> fixing it.</p>
 * <p><strong>Why</strong>: building a regression test helps to understand the actual problem - also you will never see this defect again.</p>
 * <p>You may reference the ticket in a JavaDoc comment, the method name however should rather explain which exact circumstance is tested and not contain any issue id.</p>
 * <br>
 *
 * <h3>Make private methods package visible for testing</h3>
 * <p><strong>What</strong>: if a private methods should be unit-tested, change it's visibility to default.</p>
 * <p><strong>Why</strong>: this allows for relatively easy access for the test code while still adhering to encapsulation for the most part.</p>
 * <br>
 *
 * <h3>Don't test methods twice indirectly</h3>
 * <p><strong>What</strong>: if one method calls another, only test the added functionality rather than all possible inputs again.</p>
 * <p><strong>Why</strong>: unit testing is white-box testing, so knowledge about program flows can and should be taken into account to avoid repetitions.</p>
 * <p><strong>Example</strong>: {@link #getWeightLevel()}</p>
 * <br>
 *
 * <h3>Different results for different tests</h3>
 * <p><strong>What</strong>: in tests for different functionality, the asserted result should be different as well.</p>
 * <p><strong>Why</strong>: this catches possible errors in the test code itself like calls to the wrong method and increases the number of different inputs thus also increasing test exposure.</p>
 * <p><strong>Example</strong>: {@link Reverse#threeElements()} ()} and {@link Sorted#threeElements()}</p>
 * <br>
 *
 * <h3>Different fields should contain different values</h3>
 * <p><strong>What</strong>: in tests or initializations where attributes are set, each attribute should have a different value.</p>
 * <p><strong>Why</strong>: this catches accidental swaps and makes fields easier identifiable in outputs.</p>
 * <p><strong>Example</strong>: {@link #initObject()}</p>
 * <br>
 */
public class UT02_WhatToTest {

  private UT02_WhatTo ut02;

  @BeforeEach
  void initObject() {
    // not: ut02 = new UT02_WhatTo(1, 1);
    ut02 = new UT02_WhatTo(7, 54);
  }

  @Test
  void getWeightLevel() {
    ut02.setValue(0);

    assertThat(ut02.getWeight()).isEqualTo(LIGHT);
  }

  @Nested
  class Defaults {

    @Test
    void key() {
      assertThat(ut02.getKey()).isEqualTo(7);
    }

    @Test
    void value() {
      assertThat(ut02.getValue()).isEqualTo(54);
    }
  }

  @Nested
  class CalculateWeightLevel {

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void light(int weight) {
      assertThat(calculateWeightLevel(weight)).isEqualTo(LIGHT);
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 24, 100})
    void medium(int weight) {
      assertThat(calculateWeightLevel(weight)).isEqualTo(MEDIUM);
    }

    @ParameterizedTest
    @ValueSource(ints = {101, Integer.MAX_VALUE})
    void heavy(int weight) {
      assertThat(calculateWeightLevel(weight)).isEqualTo(HEAVY);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1})
    void invalid(int weight) {
      assertThatThrownBy(() -> calculateWeightLevel(weight)).isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class ReverseNumbers {

    @Test
    void threeElements() {
      assertThat(reverseNumbers(3, 2, 1)).containsExactly(1, 2, 3);
    }
  }

  @Nested
  class SortNumbers {

    @Test
    void threeElements() {
      // not: assertThat(sortNumbers(2, 3, 1)).containsExactly(1, 2, 3);
      assertThat(sortNumbers(2, 3, 1, 4)).containsExactly(1, 2, 3, 4);
    }
  }
}
