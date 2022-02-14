package assertj.core.test.data;

import java.util.Date;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class Person {
  public Date dateOfBirth;
  public String name;
  public Optional<String> phone;
  public OptionalInt age;
  public OptionalLong id;
  public OptionalDouble weight;
  public Person neighbour;

  public Person() {}

  public Person(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Person [dateOfBirth=%s, name=%s, phone=%s, home=%s]", dateOfBirth, name, phone);
  }
}
