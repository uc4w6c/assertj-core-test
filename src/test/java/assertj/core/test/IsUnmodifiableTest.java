package assertj.core.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IsUnmodifiableTest {
  @Test
  void isUnmodifiableテスト() {
    // assertions succeed
    assertThat(Collections.unmodifiableCollection(new ArrayList<>())).isUnmodifiable();
    assertThat(Collections.unmodifiableList(new ArrayList<>())).isUnmodifiable();
    assertThat(Collections.unmodifiableSet(new HashSet<>())).isUnmodifiable();

    assertThat(List.of("")).isUnmodifiable();
    assertThat(Arrays.asList("").stream().toList()).isUnmodifiable();
    assertThat(Set.of("")).isUnmodifiable();

    // assertions fail
    // assertThat(new ArrayList<>()).isUnmodifiable();
    // assertThat(new HashSet<>()).isUnmodifiable();
  }
}
