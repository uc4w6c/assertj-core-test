package assertj.core.test.iterable;

import java.util.List;
import java.util.OptionalInt;

import assertj.core.test.data.PersonRecord;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsingElementComparatorTest {
  /**
   * usingElementComparator を利用することでequalsメソッドを利用せずに値の比較ができる
   */
  @Test
  void usingElementComparatorTest() {
    var ichi = new PersonRecord("一郎", null, OptionalInt.of(18), null, null, null);
    var ni = new PersonRecord("二郎", null, OptionalInt.of(18), null, null, null);
    var san = new PersonRecord("三郎", null, OptionalInt.of(18), null, null, null);
    var actual = List.of(ichi, ni);

    assertThat(actual).contains(ichi)
        .doesNotContain(san);

    assertThat(actual).usingElementComparator((t1, t2) ->
                        Integer.compare(t1.age().getAsInt(), t2.age().getAsInt()))
                      .contains(san);
  }
}
