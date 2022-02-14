package assertj.core.test;

import assertj.core.test.data.Person;
import assertj.core.test.data.PersonRecord;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HasSuperClassTest {
  @Test
  void Recordのスーパークラスをテスト() {
    // var personRecord = new PersonRecord("taro", null, null, null, null, null);
    // assertThat(PersonRecord.class).hasNoSuperclass();
    assertThat(Person.class).hasNoSuperclass();
  }
}
