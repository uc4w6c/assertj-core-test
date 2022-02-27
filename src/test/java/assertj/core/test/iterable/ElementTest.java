package assertj.core.test.iterable;

import java.util.List;

import org.assertj.core.api.StringAssert;
import org.junit.jupiter.api.Test;

import static assertj.core.test.util.AssertionsUtil.expectAssertionError;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.INTEGER;
import static org.assertj.core.api.InstanceOfAssertFactories.STRING;

public class ElementTest {
  @Test
  void elementTest() {
    var actual = List.of("一郎", "二郎", "三郎", "四郎", "五郎");

    // firstにasを指定しないと型が確定できないため共通クラスのメソッドしか利用できない
    assertThat(actual).first()
                      .isEqualTo("一郎");

    // asを指定することで型が確定し、そのStringクラスのassertionが利用できる
    assertThat(actual).first(as(STRING))
        .startsWith("一");
    assertThat(actual).element(1, as(STRING))
                      .startsWith("二");
    assertThat(actual).last(as(STRING))
                      .startsWith("五");

    // assertThatで事前に型を指定することで、Stringクラスのassertionが利用できる
    assertThat(actual, StringAssert.class).first()
                                          .startsWith("一");
    assertThat(actual, StringAssert.class).first(as(STRING))
                                          .startsWith("一");
  }

  @Test
  void elementIntTest() {
    var actual = List.of(1, 2, 3);

    assertThat(actual).first(as(INTEGER))
                      .isEqualTo(1);
    AssertionError assertionError = expectAssertionError(() -> assertThat(actual).element(1, as(STRING))
                      .isEqualTo(2));
    then(assertionError).hasMessageContaining("to be an instance of:");
  }

  @Test
  void singleElementTest() {
    var errorActual = List.of("一郎", "二郎", "三郎", "四郎", "五郎");
    AssertionError assertionError = expectAssertionError(() -> assertThat(errorActual).singleElement().isEqualTo("一郎"));
    then(assertionError).hasMessageContaining("Expected size: 1 but was: ");

    var actual = List.of("一郎");

    assertThat(actual).singleElement().isEqualTo("一郎");
    assertThat(actual).singleElement(as(STRING))
        .startsWith("一");
  }
}
