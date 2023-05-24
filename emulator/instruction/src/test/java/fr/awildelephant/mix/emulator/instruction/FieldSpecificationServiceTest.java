package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.Word;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FieldSpecificationServiceTest {

    private final FieldSpecificationService fieldSpecificationService = new FieldSpecificationService();

    @ParameterizedTest
    @MethodSource("loadScenarios")
    void load(FieldSpecification specification, Word source, Word expected) {
        assertThat(fieldSpecificationService.load(specification, source)).isEqualTo(expected);
    }

    private static Stream<Arguments> loadScenarios() {
        final Word source = new Word(false, (byte) 5, (byte) 0, (byte) 3, (byte) 5, (byte) 4);
        return Stream.of(
                Arguments.of(new FieldSpecification(0, 5), source, source),
                Arguments.of(new FieldSpecification(1, 5), source, new Word(true, (byte) 5, (byte) 0, (byte) 3, (byte) 5, (byte) 4)),
                Arguments.of(new FieldSpecification(3, 5), source, new Word(true, (byte) 0, (byte) 0, (byte) 3, (byte) 5, (byte) 4)),
                Arguments.of(new FieldSpecification(0, 3), source, new Word(false, (byte) 0, (byte) 0, (byte) 5, (byte) 0, (byte) 3)),
                Arguments.of(new FieldSpecification(4, 4), source, new Word(true, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 5)),
                Arguments.of(new FieldSpecification(0, 0), source, new Word(false, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0))
        );
    }

    @ParameterizedTest
    @MethodSource("storeScenarios")
    void store(FieldSpecification specification, Word source, Word destination, Word expected) {
        assertThat(fieldSpecificationService.store(specification, source, destination)).isEqualTo(expected);
    }

    private static Stream<Arguments> storeScenarios() {
        final Word source = new Word(true, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 0);
        final Word destination = new Word(false, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        return Stream.of(
                Arguments.of(new FieldSpecification(0, 5), source, destination, source),
                Arguments.of(new FieldSpecification(1, 5), source, destination, new Word(false, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 0)),
                Arguments.of(new FieldSpecification(5, 5), source, destination, new Word(false, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 0)),
                Arguments.of(new FieldSpecification(2, 2), source, destination, new Word(false, (byte) 1, (byte) 0, (byte) 3, (byte) 4, (byte) 5)),
                Arguments.of(new FieldSpecification(2, 3), source, destination, new Word(false, (byte) 1, (byte) 9, (byte) 0, (byte) 4, (byte) 5)),
                Arguments.of(new FieldSpecification(0, 1), source, destination, new Word(true, (byte) 0, (byte) 2, (byte) 3, (byte) 4, (byte) 5))
        );
    }
}