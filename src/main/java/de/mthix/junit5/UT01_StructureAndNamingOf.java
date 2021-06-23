package de.mthix.junit5;

import java.util.List;

import static java.util.Arrays.asList;

class UT01_StructureAndNamingOf {

  private int     complexity = 0;
  private boolean changed    = false;

  public List<String> toList(String... args) {
    return asList(args);
  }

  public String[] toArray(String... args) {
    return args;
  }

  public void increaseComplexity() {
    complexity++;
    changed = true;
  }

  public int getComplexity() {
    return complexity;
  }

  public boolean isChanged() {
    return changed;
  }
}
