package assertj.core.test.iterable;

import java.util.List;
import java.util.OptionalInt;

import assertj.core.test.data.PersonRecord;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.not;

public class FilterTest {
  @Test
  void filterTest() {
    var actual = List.of("一郎", "二郎", "太郎", "花子");

    assertThat(actual).filteredOn(name -> name.endsWith("郎"))
        .hasSize(3);
  }

  @Test
  void filterOnFieldTest() {
    var actual = List.of(
        new PersonRecord("一郎", null, OptionalInt.of(18), null, null, null),
        new PersonRecord("二郎", null, OptionalInt.of(19), null, null, null),
        new PersonRecord("三郎", null, OptionalInt.of(20), null, null, null),
        new PersonRecord("四郎", null, OptionalInt.of(21), null, null, null),
        new PersonRecord("五郎", null, OptionalInt.of(22), null, null, null)
    );

    assertThat(actual).filteredOn("age", OptionalInt.of(18))
        .hasSize(1);
    assertThat(actual).filteredOn("age", not(OptionalInt.of(18)))
                      .hasSize(4);
  }

  @Test
  void filteredOnAssertionsTest() {
    var actual = List.of("一郎", "二郎", "太郎", "花子");

    assertThat(actual).filteredOnAssertions(name -> assertThat(name).startsWith("一"))
        .contains("一郎");
    assertThat(actual).filteredOnAssertions(name -> assertThat(name).startsWith("六"))
        .hasSize(0);
  }

  @Test
  void conditionTest() {
    var actual = List.of("一郎", "二郎", "太郎", "花子");

    var condition = new Condition<String>(name -> {
      return name.endsWith("郎") || name.equals("太郎");
    }, "test");
    assertThat(actual).filteredOn(condition)
        .hasSize(3);
  }
}
