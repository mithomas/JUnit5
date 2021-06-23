/**
 * <h2>JUnit 5 Best Practices with Spring Boot</h2>
 *
 * <h3>General Testing Approach</h3>
 *
 * <pre>
 *                           /-----\
 *                          / Expl- \
 *                         / orative \
 *                        /   Tests   \
 *                       /-------------\
 *                      /     User      \
 *                     /    Acceptance   \
 *                    /       Tests       \
 *                   /---------------------\
 *                  /        System         \
 *                 /       Integration       \
 *                /          Tests            \
 *               /-----------------------------\
 *              /                               \    -------------|
 *             /       Component E2E Test	       \                | * "Developer Tests"
 *            /                                   \               |
 *           /-------------------------------------\              | * run as part of a build
 *          /                                       \             |
 *         /       Component Integration Tests       \            |
 *        /                                           \           |
 *       /---------------------------------------------\          |
 *      /                                               \         |
 *     /                                                 \        |
 *    /                    Unit Tests                     \       |
 *   /                                                     \      |
 *  /                                                       \    -|
 * /---------------------------------------------------------\
 * </pre>
 * <p>
 * From bottom to top:
 * <ul>
 *   <li>fewer, but larger test cases</li>
 *   <li>less automation</li>
 *   <li>less white-box, more black-box testing</li>
 *   <li>less isolation, more external dependencies</li>
 *   <li>higher runtime</li>
 * </ul>
 * <p>Further reading:</p>
 * <ul>
 *   <li><a href="https://martinfowler.com/articles/practical-test-pyramid.html">The Practical Test Pyramid</a></li>
 * </ul>
 *
 * <h4>Unit Tests</h4>
 * <strong>Why?</strong>
 * <ul>
 *   <li>have a basis for risk-free and confident changes or refactorings</li>
 *   <li>show others how the code works</li>
 *   <li>improve code quality - code that's hard to test (usually) has issues</li>
 * </ul>
 *
 * <strong>How?</strong>
 * <ul>
 *   <li>focused on small, isolated units: classes and methods</li>
 *   <li>as (sensibly) complete as possible</li>
 *   <li>in a structured way</li>
 *   <li>out-of-container: no Spring, no in-memory components - just POJOs and mocks</li>
 * </ul>
 *
 * <strong>What?</strong>
 * <ul>
 *   <li>test cases are directly derived from the implementation logic</li>
 * </ul>
 */
package de.mthix.junit5;