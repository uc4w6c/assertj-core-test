package assertj.core.test.iterable;

import java.util.List;

import org.junit.jupiter.api.Test;

import static assertj.core.test.util.AssertionsUtil.expectAssertionError;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class SatisfyTest {
  /**
   * allSatisfy はリストの値を一つずつテストして全て成功していることをテストする
   */
  @Test
  void allSatisfyTest() {
    var actual = List.of("一郎", "二郎", "三郎", "四郎", "五郎");
    assertThat(actual).allSatisfy(name -> {
      assertThat(name.length()).isEqualTo(2);
    });

    AssertionError assertionError = expectAssertionError(() -> {
      assertThat(actual).allSatisfy(name -> {
        assertThat(name).isEqualTo("一郎");
      });
    });
    then(assertionError).hasMessageContaining("to satisfy given requirements, but these elements did not:");
  }

  /**
   * anySatisfy はリストの値を一つずつテストして一つ以上成功していることをテストする
   */
  @Test
  void anySatisfyTest() {
    var actual = List.of("一郎", "二郎", "三郎", "四郎", "五郎");
    assertThat(actual).anySatisfy(name -> {
      assertThat(name).isEqualTo("一郎");
    });

    AssertionError assertionError = expectAssertionError(() -> {
      assertThat(actual).anySatisfy(name -> {
        assertThat(name).isEqualTo("六郎");
      });
    });
    then(assertionError).hasMessageContaining("to satisfy the given assertions requirements but none did:");
  }

  /**
   * noneSatisfy はリストの値を一つずつテストして全て一致しないことをテストします
   */
  @Test
  void noneSatisfyTest() {
    var actual = List.of("一郎", "二郎", "三郎", "四郎", "五郎");
    assertThat(actual).noneSatisfy(name -> {
      assertThat(name).isEqualTo("六郎");
    });

    AssertionError assertionError = expectAssertionError(() -> {
      assertThat(actual).noneSatisfy(name -> {
        assertThat(name).isEqualTo("一郎");
      });
    });
    then(assertionError).hasMessageContaining("to satisfy the given assertions requirements but these elements did:");
  }
}
