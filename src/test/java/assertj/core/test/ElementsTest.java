package assertj.core.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementsTest {
  public static record Person(String name, int age) {}
  @Test
  void ネストでもOK() {
    var taro = new Person("taro", 15);
    var hanako = new Person("hanako", 16);
    var takuya = new Person("takuya", 17);
    var takuma = new Person("takuma", 18);
    var mai = new Person("mai", 19);

    var personList = List.of(
        taro, hanako, takuya, takuma, mai);
    assertThat(personList).elements(0, 1, 4)
        .elements(0, 2)
        .hasSize(2)
        .containsExactly(taro, mai);

    // to be greater than 5 but was 3が出力しエラーになる(適切な挙動)
    // assertThat(personList).elements(0, 1, 4)
    //                       .elements(0, 5)
    //                       .hasSize(2)
    //                       .containsExactly(taro, mai);
  }
}
