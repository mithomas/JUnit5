package de.mthix.junit5;

import de.mthix.junit5.UT03_UseTheRightToolsFor.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.AssertionErrors;

import static de.mthix.junit5.UT03_UseTheRightToolsFor.convertSizeCode;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * <h2>Use your tools</h2>
 *
 * <h3>Use JUnit's parameterized tests</h3>
 * <p><strong>What</strong>: for identical test code, use parameterized tests.</p>
 * <p><strong>Why</strong>: this avoids duplicate code, makes extensions easier and also leads to cleaner test reports.</p>
 * <p><strong>Example</strong>: {@link ConvertSizeCode#invalid(String)}</p>
 * <p>Further reading:</p>
 * <ul>
 *   <li><a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests">JUnit 5 User Guide on Parameterized Tests</a></li>
 * </ul>
 * <br>
 *
 * <h3>Use JUnit's {@link NullAndEmptySource} annotation</h3>
 * <p><strong>What</strong>: test default string behaviour with JUnit's {@link NullAndEmptySource} annotation.</p>
 * <p><strong>Why</strong>: in combination with parameterized tests this makes for less boilerplate code.</p>
 * <p><strong>Example</strong>: {@link ConvertSizeCode#invalid(String)}</p>
 * <br>
 *
 * <h3>Use Mockito to mock external dependencies as much as required, but not more</h3>
 * <p><strong>What</strong>: mock out what is required for a test to develop, ignore the rest.</p>
 * <p><strong>Why</strong>: this decouples classes from each other while still making tests robust against refactoring.</p>
 * <p>Using Mockito's annotations for creating and injecting mock objects further avoids boilerplate code.</p>
 * <p><strong>Example</strong>: {@link UT03_UseTheRightToolsForTheTest}</p>
 * <p>Further reading:</p>
 * <ul>
 *   <li><a href="https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html">Mockito Reference Documentation</a></li>
 * </ul>
 * <br>
 *
 * <h3>Use the most specific AssertJ assertion possible</h3>
 * <p><strong>What</strong>: instead of always performing an equality check, make use of the various matchers of AssertJ.</p>
 * <p><strong>Why</strong>: to make the test code more readable and provide better assertion messages.</p>
 * <p><strong>Example</strong>: {@link AssertJSamples}</p>
 * <p>This is especially true when dealing with collections, where AssertJ provides a variety of matchers for different use cases.</p>
 * <p>Further reading:</p>
 * <ul>
 *   <li><a href="https://assertj.github.io/doc/#assertj-core-assertions-guide">AssertJ Core Assertions Guide</a></li>
 * </ul>
 * <br>
 */
@ExtendWith(MockitoExtension.class)
public class UT03_UseTheRightToolsForTheTest {

  @InjectMocks
  private UT03_UseTheRightToolsFor ut03;
  @Mock
  private Actor                    mockActor;

// This is already handled by @InjectMocks
//  @BeforeEach
//  void initObjectUnderTest() {
//    ut03_useTheRightToolsFor = new UT03_UseTheRightToolsFor();
//  }

  @Nested
  class Act {

    @Nested
    class Normal {

      private Integer rc;
      private Integer result;

      @BeforeEach
      void act() {
        rc = 0;
        when(mockActor.doSomething()).thenReturn(rc);

        result = ut03.act();
      }

      @Test
      void invokedActor() {
        verify(mockActor).doSomething();
      }

      @Test
      void passedThroughRc() {
        assertThat(result).isSameAs(rc);
      }
    }
  }

  @Nested
  class ConvertSizeCode {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a", "f", "C"})
    void invalid(String code) {
      assertThatThrownBy(() -> convertSizeCode(code)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(String.valueOf(code));
    }
  }

  @Nested
  class AssertJSamples {

    @Test
    void truth() {
      // not like this:
      AssertionErrors.assertEquals("", false, false);

      // rather:
      assertThat(false).isFalse();
    }

    @Test
    void contains() {
      // not like this:
      AssertionErrors.assertTrue("", asList(0, 1, 2, 3, 4, 5).containsAll(asList(3, 5, 4)));

      // rather:
      assertThat(asList(0, 1, 2, 3, 4, 5)).contains(3, 5, 4);
    }
  }
}
