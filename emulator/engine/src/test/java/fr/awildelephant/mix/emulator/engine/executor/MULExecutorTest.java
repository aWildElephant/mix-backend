package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.helper.WordStringRepresentation;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.MachineBuilder;
import fr.awildelephant.mix.emulator.word.Word;
import fr.awildelephant.mix.emulator.word.WordHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static fr.awildelephant.mix.emulator.engine.helper.ExecutorTestHelper.execute;
import static org.assertj.core.api.Assertions.assertThat;

class MULExecutorTest {

    /**
     * page 132 first MUL example
     */
    @Test
    void test1() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(Word.from(true, 1, 1, 1, 1, 1))
                .withMemoryCell(1000, Word.from(true, 1, 1, 1, 1, 1))
                .build();

        execute("MUL 1000", machine);

        // TODO: build asserter for Machine
        assertThat(machine.registerA().content()).isEqualTo(Word.from(true, 0, 1, 2, 3, 4));
        assertThat(machine.registerX().content()).isEqualTo(Word.from(true, 5, 4, 3, 2, 1));
        assertThat(machine.overflowToggle().state()).isFalse();
    }

    /**
     * page 132 second MUL example
     */
    @Disabled("parse error")
    @Test
    void test2() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(WordHelper.toWord(-112))
                .withMemoryCell(1000, Word.from(true, 2, 0, 0, 0, 0))
                .build();

        execute("MUL 1000(1:1)", machine);

        assertThat(machine.registerA().content()).isEqualTo(Word.from(false, 0, 0, 0, 0, 0));
        assertThat(machine.registerX().content()).isEqualTo(WordHelper.toWord(-224));
        assertThat(machine.overflowToggle().state()).isFalse();
    }

    /**
     * page 132 third MUL example
     */
    @Test
    void test3() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(WordStringRepresentation.toWord("[-|50|0|,112|4]"))
                .withMemoryCell(1000, WordStringRepresentation.toWord("[-|2|0|0|0|0]"))
                .build();

        execute("MUL 1000", machine);

        assertThat(machine.registerA().content()).isEqualTo(WordStringRepresentation.toWord("[+|,100|0|,224]"));
        assertThat(machine.registerX().content()).isEqualTo(WordStringRepresentation.toWord("[+|8|0|0|0|0]"));
        assertThat(machine.overflowToggle().state()).isFalse();
    }
}