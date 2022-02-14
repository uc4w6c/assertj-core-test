package assertj.core.test;

import assertj.core.test.data.PersonRecord;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsingRecursiveComparisonTest {
  @Test
  void record比較() {
    var personRecord1 = new PersonRecord("taro", null, null, null, null, null);
    var personRecord2 = new PersonRecord("taro", null, null, null, null, personRecord1);

    var personRecord3 = new PersonRecord("taro", null, null, null, null, null);
    var personRecord4 = new PersonRecord("hanako", null, null, null, null, personRecord3);

    // 想定通りエラーが返る
    assertThat(personRecord2)
        .usingRecursiveComparison()
        .isEqualTo(personRecord4);
  }
}
