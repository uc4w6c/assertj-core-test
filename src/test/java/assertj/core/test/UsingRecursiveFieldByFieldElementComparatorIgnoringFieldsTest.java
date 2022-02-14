package assertj.core.test;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsingRecursiveFieldByFieldElementComparatorIgnoringFieldsTest {
  public static class Person {
    private String firstName;
    private String lastName;
    public Person neighbour;

    public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public Person(String firstName, String lastName, Person neighbour) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.neighbour = neighbour;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }
  }

  @Test
  void firstNameを除外() {
    var actualNeighbour = new Person("takahashi", "taro");
    var actualNeighbourNeighbour = new Person("yamada", "taro", actualNeighbour);
    var actualPerson = new Person("sato", "hanako", actualNeighbourNeighbour);

    var expectNeighbour = new Person("yamada", "taro");
    var expectNeighbourNeighbour = new Person("takahashi", "taro", expectNeighbour);
    var expectPerson = new Person("sato", "hanako", expectNeighbourNeighbour);

    assertThat(actualPerson).usingRecursiveComparison()
        .ignoringFields("firstName")
        .isEqualTo(expectPerson);
  }
}
