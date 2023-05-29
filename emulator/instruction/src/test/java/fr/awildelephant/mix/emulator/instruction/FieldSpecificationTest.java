package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.Word;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FieldSpecificationTest {

    @ParameterizedTest
    @MethodSource("loadScenarios")
    void load(FieldSpecification specification, Word source, Word expected) {
        assertThat(specification.load(source)).isEqualTo(expected);
    }

    private static Stream<Arguments> loadScenarios() {
        final Word source = Word.from(false, 5, 0, 3, 5, 4);
        return Stream.of(
                Arguments.of(new FieldSpecification(0, 5), source, source),
                Arguments.of(new FieldSpecification(1, 5), source, Word.from(true, 5, 0, 3, 5, 4)),
                Arguments.of(new FieldSpecification(3, 5), source, Word.from(true, 0, 0, 3, 5, 4)),
                Arguments.of(new FieldSpecification(0, 3), source, Word.from(false, 0, 0, 5, 0, 3)),
                Arguments.of(new FieldSpecification(4, 4), source, Word.from(true, 0, 0, 0, 0, 5)),
                Arguments.of(new FieldSpecification(0, 0), source, Word.from(false, 0, 0, 0, 0, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("storeScenarios")
    void store(FieldSpecification specification, Word source, Word destination, Word expected) {
        assertThat(specification.store(source, destination)).isEqualTo(expected);
    }

    private static Stream<Arguments> storeScenarios() {
        final Word source = Word.from(true, 6, 7, 8, 9, 0);
        final Word destination = Word.from(false, 1, 2, 3, 4, 5);
        return Stream.of(
                Arguments.of(new FieldSpecification(0, 5), source, destination, source),
                Arguments.of(new FieldSpecification(1, 5), source, destination, Word.from(false, 6, 7, 8, 9, 0)),
                Arguments.of(new FieldSpecification(5, 5), source, destination, Word.from(false, 1, 2, 3, 4, 0)),
                Arguments.of(new FieldSpecification(2, 2), source, destination, Word.from(false, 1, 0, 3, 4, 5)),
                Arguments.of(new FieldSpecification(2, 3), source, destination, Word.from(false, 1, 9, 0, 4, 5)),
                Arguments.of(new FieldSpecification(0, 1), source, destination, Word.from(true, 0, 2, 3, 4, 5))
        );
    }
}