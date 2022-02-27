package assertj.core.test.iterable;

import java.util.List;
import java.util.OptionalInt;
import java.util.OptionalLong;

import assertj.core.test.data.PersonRecord;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ExtractingTest {
  @Test
  void ageTest() {
    var actual = List.of(
        new PersonRecord("一郎", null, OptionalInt.of(18), null, null, null),
        new PersonRecord("二郎", null, OptionalInt.of(19), null, null, null),
        new PersonRecord("三郎", null, OptionalInt.of(20), null, null, null),
        new PersonRecord("四郎", null, OptionalInt.of(21), null, null, null),
        new PersonRecord("五郎", null, OptionalInt.of(22), null, null, null)
    );

    assertThat(actual).extracting("age")
        .contains(OptionalInt.of(18), OptionalInt.of(19));

    assertThat(actual).extracting("age", OptionalInt.class)
                      .contains(OptionalInt.of(18), OptionalInt.of(19));
  }

  /**
   * 複数のフィールドをテストする場合はAssertions.tupleを利用する
   */
  @Test
  void multiFieldTest() {
    var actual = List.of(
        new PersonRecord("一郎", null, OptionalInt.of(18), OptionalLong.of(1), null, null),
        new PersonRecord("二郎", null, OptionalInt.of(19), null, null, null),
        new PersonRecord("三郎", null, OptionalInt.of(20), null, null, null),
        new PersonRecord("四郎", null, OptionalInt.of(21), null, null, null),
        new PersonRecord("五郎", null, OptionalInt.of(22), null, null, null)
    );

    assertThat(actual).extracting("name", "age", "id")
                      .contains(tuple("一郎", OptionalInt.of(18), OptionalLong.of(1)),
                                tuple("二郎", OptionalInt.of(19), null));
  }
}
