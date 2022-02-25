package assertj.core.test.iterable;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static assertj.core.test.util.AssertionsUtil.expectAssertionError;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class ContainsTest {
  private List<String> actual = List.of("一郎", "二郎", "三郎", "四郎", "五郎");

  /**
   * contains は含んでいるかをテストする
   */
  @Test
  void containsTest() {
    assertThat(actual).contains("一郎", "二郎");

    AssertionError assertionError = expectAssertionError(() -> assertThat(actual).contains("六郎"));
    then(assertionError).hasMessageContaining("could not find the following element(s):");
  }

  /**
   * containsOnly は指定した要素のみがあることをテストする
   * 重複は無視して1つの物として扱う
   */
  @Test
  void containsOnlyTest() {
    List<String> duplicateActual = new ArrayList<>(actual);
    duplicateActual.add("一郎");

    assertThat(duplicateActual).containsOnly("一郎", "二郎", "三郎", "四郎", "五郎");
    assertThat(duplicateActual).containsOnly("一郎", "二郎", "二郎", "三郎", "四郎", "五郎");

    AssertionError assertionError = expectAssertionError(() -> assertThat(duplicateActual).containsOnly("二郎", "三郎", "四郎", "五郎"));
    then(assertionError).hasMessageContaining("but the following element(s) were unexpected:");
  }

  /**
   * containsExactly は順番・要素数含めて完全に一致することをテストする
   */
  @Test
  void containsExactlyTest() {
    assertThat(actual).containsExactly("一郎", "二郎", "三郎", "四郎", "五郎");

    AssertionError assertionError = expectAssertionError(() -> assertThat(actual).containsExactly("二郎", "三郎", "四郎", "五郎", "一郎"));
    then(assertionError).hasMessageContaining("Actual and expected have the same elements but not in the same order,");
  }

  /**
   * containsExactlyInAnyOrder は順番は無視するが中身の要素は数も含めて一致しているかテストする
   */
  @Test
  void containsExactlyInAnyOrderTest() {
    List<String> duplicateActual = new ArrayList<>(actual);
    duplicateActual.add("一郎");

    assertThat(duplicateActual).containsExactlyInAnyOrder("一郎", "一郎", "二郎", "三郎", "五郎", "四郎");
    assertThat(duplicateActual).containsExactlyInAnyOrder("一郎", "二郎", "三郎", "四郎", "五郎", "一郎");

    AssertionError assertionError = expectAssertionError(() -> assertThat(duplicateActual).containsExactlyInAnyOrder("一郎", "二郎", "三郎", "四郎", "五郎"));
    then(assertionError).hasMessageContaining("but the following elements were unexpected:");

    AssertionError assertionError2 = expectAssertionError(() -> assertThat(duplicateActual).containsExactlyInAnyOrder("一郎", "二郎", "三郎", "五郎", "四郎", "一郎", "二郎"));
    then(assertionError2).hasMessageContaining("but the following elements were unexpected:");
  }
}
