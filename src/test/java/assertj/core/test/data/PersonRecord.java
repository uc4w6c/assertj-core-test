package assertj.core.test.data;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public record PersonRecord(
    String name,
    Optional<String> phone,
    OptionalInt age,
    OptionalLong id,
    OptionalDouble weight,
    PersonRecord neighbour
) {}
