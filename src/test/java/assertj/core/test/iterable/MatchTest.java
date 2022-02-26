package assertj.core.test.iterable;

import java.util.List;

import org.junit.jupiter.api.Test;

import static assertj.core.test.util.AssertionsUtil.expectAssertionError;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class MatchTest {
  @Test
  void matchTest() {
    var actual = List.of("一郎", "二郎", "三郎", "四郎", "五郎");

    // allMatch
    assertThat(actual).allMatch(name -> name.endsWith("郎"));
    AssertionError assertionError = expectAssertionError(() -> {
      assertThat(actual).allMatch(name -> name.startsWith("郎"));
    });
    then(assertionError).hasMessageContaining("to match given predicate but these elements did not:");

    // anyMatch
    assertThat(actual).anyMatch(name -> name.startsWith("一"));
    AssertionError anyMatchAssertionError = expectAssertionError(() -> {
      assertThat(actual).anyMatch(name -> name.startsWith("六"));
    });
    then(anyMatchAssertionError).hasMessageContaining("to match given predicate but none did.");

    // noneMatch
    assertThat(actual).noneMatch(name -> name.startsWith("六"));
    AssertionError noneMatchAssertionError = expectAssertionError(() -> {
      assertThat(actual).noneMatch(name -> name.startsWith("一"));
    });
    then(anyMatchAssertionError).hasMessageContaining("to match given predicate but none did.");
  }
}
