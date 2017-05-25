import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParametrizedDemoTest {
    
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = {"SECONDS", "MINUTES"})
    void testWithParameters(TimeUnit s) {
        assertNotNull(s);
    }

    @ParameterizedTest
    @MethodSource(names = "stringAndIntProvider")
    void testWithMultiArgMethodSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    static Stream<Arguments> stringAndIntProvider() {
        return Stream.of(
                ObjectArrayArguments.create("foo", 1),
                ObjectArrayArguments.create("bar", 2)
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv")
    void testWithCsvFileSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
    }
}
