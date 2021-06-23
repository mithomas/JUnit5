package de.mthix.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <h2>Structure and Naming</h2>
 *
 * <h3>One test class per main class</h3>
 * <p><strong>What</strong>: have one test class per class to test named {@code <classname>Test}.</p>
 * <p><strong>Why</strong>: to have a clearly defined place.f</p>
 * <p>This also means: If the base class is renamed or split, the tests should follow accordingly.</p>
 * <p><strong>Example</strong>: {@link UT01_StructureAndNamingOfTest}</p>
 * <br>
 *
 * <h3>Nested classes per method</h3>
 * <p><strong>What</strong>: use JUnit's {@link Nested} annotation to build an inner test class per method.</p>
 * <p><strong>Why</strong>: this leads to both readable test code and output and allows for a clear place to add missing tests later.</p>
 * <p>This also means: If the method is renamed or refactored, the tests should follow accordingly.</p>
 * <p>Exception: if there really is only one test case for a given method (e.g. for simple wrapper methods), a single top-level-method suffices.</p>
 * <p><strong>Example</strong>: {@link ToList}</p>
 * <br>
 *
 * <h3>Functionally relevant test method names</h3>
 * <p><strong>What</strong>: choose test names that describe what is tested while avoiding meaningless and redundant names.</p>
 * <p><strong>Why</strong>: to have clear understanding of what is tested and what went wrong in case of a later error, both in the IDE and in the test reports.</p>
 * <p>Avoid things like <em>testToList_2</em>:</p>
 * <ul>
 *   <li><em>test...</em> is redundant - you know you are in a test class</li>
 *   <li><em>..ToList...</em> is redundant - you are in the accordingly named inner class</li>
 *   <li><em>..._1</em> is meaningless - rather describe what is actually tested</li>
 * </ul>
 * <p><strong>Example</strong>: {@link GetComplexity#twiceIncreased()}</p>
 * <br>
 *
 * <h3>Re-build test setup for each test</h3>
 * <p><strong>What</strong>: the object under test should be reset for each no test..</p>
 * <p><strong>Why</strong>: this avoids any side-effects when running all or only part of the tests.</p>
 * <p>Usually you want to init you object under test within a {@link BeforeEach}-method.</p>
 * <p><strong>Example</strong>: {@link #initUT01_StructureAndNaming()}</p>
 * <br>
 *
 * <h3>Functionally relevant setup method names</h3>
 * <p><strong>What</strong>: choose method names that describe what is initialized while avoiding meaningless and redundant names.</p>
 * <p><strong>Why</strong>: to have clear understanding of what is set up.</p>
 * <p>Avoid things like <em>setUp</em> which tell you nothing about what is actually set up. In case of preparation of different kinds of data or different scenarios feel free to use multiple setup methods.</p>
 * <p><strong>Example</strong>: {@link #initUT01_StructureAndNaming()}</p>
 * <br>
 *
 * <h3>Follow the AAA-pattern for complex tests</h3>
 * <p><strong>What</strong>: first set up your test conditions ("arrange"), then execute the test ("act"), then "assert" the result.</p>
 * <p><strong>Why</strong>: to have a consistent structure across all units tests for easier and thus faster understanding of test method.</p>
 * <p>For simple tests, act and assertion may be combined, for even simpler ones this might even be a one-liner.</p>
 * <p><strong>Example</strong>: {@link GetComplexity#twiceIncreased()}</p>
 * <p><strong>Further reading</strong>:</p>
 * <ul>
 *   <li><a href="https://freecontent.manning.com/making-better-unit-tests-part-1-the-aaa-pattern/">Making Better Unit Tests: part 1, the AAA pattern</a></li>
 * </ul>
 * <br>
 *
 * <h3>Only one assert per test</h3>
 * <p><strong>What</strong>: there should only be one assertion for any test method</p>
 * <p><strong>Why</strong>: multiple assertions per test could mask consecutive errors and make it unclear what is actually tested.</p>
 * <p>If one action leads to multiple outcomes to be asserted, move the setup into a distinct {@code BeforeEach}-method. Remember that they can exist on multiple nested levels.</p>
 * <p><strong>Example</strong>: {@link IncreaseComplexity}</p>
 * <br>
 */
public class UT01_StructureAndNamingOfTest {

  private UT01_StructureAndNamingOf ut01;

  @BeforeEach
  void initUT01_StructureAndNaming() {
    ut01 = new UT01_StructureAndNamingOf();
  }

  @Nested
  class ToList {

    @Test
    void single() {
      assertThat(ut01.toList("a")).containsExactly("a");
    }

    @Test
    void multiple() {
      assertThat(ut01.toList("a", "b")).containsExactly("a", "b");
    }
  }

  @Nested
  class ToArray {

    @Test
    void single() {
      assertThat(ut01.toArray("a")).containsExactly("a");
    }

    @Test
    void multiple() {
      assertThat(ut01.toArray("a", "b")).containsExactly("a", "b");
    }
  }

  @Nested
  class GetComplexity {

    @Test
    void initial() {
      assertThat(ut01.getComplexity()).isEqualTo(0);
    }

    @Test
    void twiceIncreased() {
      ut01.increaseComplexity();
      ut01.increaseComplexity();

      int increasedComplexity = ut01.getComplexity();

      assertThat(increasedComplexity).isEqualTo(2);
    }
  }

  @Nested
  class IncreaseComplexity {

    @BeforeEach
    void increaseComplexity() {
      ut01.increaseComplexity();
    }

    @Test
    void changed() {
      assertThat(ut01.isChanged()).isTrue();
    }

    @Test
    void complexityIncreased() {
      assertThat(ut01.getComplexity()).isEqualTo(1);
    }
  }
}
