package assertj.core.test.iterable;

import java.util.List;
import java.util.OptionalInt;

import assertj.core.test.data.PersonRecord;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
  }
}
