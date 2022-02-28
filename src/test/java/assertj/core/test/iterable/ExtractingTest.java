package assertj.core.test.iterable;

import java.util.List;
import java.util.OptionalInt;
import java.util.OptionalLong;

import assertj.core.test.data.PersonRecord;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ExtractingTest {
  /**
   * extracting メソッドは対象フィールドを取得する
   */
  @Test
  void extractingTest() {
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

  /**
   * flatExtracting はListの値をflatにする
   */
  @Test
  void flatExtractingTest() {
    record Player(String name, List<String> team){};

    var taro = new Player("taro", List.of("巨人", "阪神"));
    var takao = new Player("takao", List.of("日ハム", "ソフトバンク"));
    var actual = List.of(taro, takao);

    // flatExtractingには取得するフィールド名を指定
    assertThat(actual).flatExtracting("team")
        .contains(1)
        .contains("巨人", "阪神", "日ハム", "ソフトバンク");
    // Functionを利用して型安全に確認できる
    assertThat(actual).flatExtracting(Player::team)
                      .contains("巨人", "阪神", "日ハム", "ソフトバンク");
    // flatMapはflatExtractingのalias
    assertThat(actual).flatMap(Player::team)
                      .contains("巨人", "阪神", "日ハム", "ソフトバンク");
  }

  @Test
  void flatExtractingMultiFieldTest() {
    record Player(String name, List<String> team){};

    var taro = new Player("taro", List.of("巨人", "阪神"));
    var takao = new Player("takao", List.of("日ハム", "ソフトバンク"));
    var actual = List.of(taro, takao);

    assertThat(actual).flatExtracting("name", "team")
        .contains("taro", "takao", List.of("巨人", "阪神"), List.of("日ハム", "ソフトバンク"));
  }
}
