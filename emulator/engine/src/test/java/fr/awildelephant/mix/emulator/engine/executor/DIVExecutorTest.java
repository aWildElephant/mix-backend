package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.helper.WordStringRepresentation;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.MachineBuilder;
import fr.awildelephant.mix.emulator.word.Word;
import fr.awildelephant.mix.emulator.word.WordHelper;
import org.junit.jupiter.api.Test;

import static fr.awildelephant.mix.emulator.engine.helper.ExecutorTestHelper.execute;
import static fr.awildelephant.mix.emulator.engine.testutil.CommonWords.minusZero;
import static fr.awildelephant.mix.emulator.engine.testutil.MachineAsserter.assertThatMachine;

public class DIVExecutorTest {

    /**
     * page 132 DIV example
     */
    @Test
    void test1() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(WordHelper.toWord(0))
                // sign for this value is undefined in the example
                .withXRegister(WordHelper.toWord(17))
                .withMemoryCell(1000, WordHelper.toWord(3))
                .build();

        execute("DIV 1000", machine);

        assertThatMachine(machine)
                .hasRegisterA(WordHelper.toWord(5))
                .hasRegisterX(WordHelper.toWord(2));
    }

    /**
     * page 133 DIV example
     */
    @Test
    void test2() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(minusZero())
                .withXRegister(WordStringRepresentation.toWord("[+|,1235|0|3|1]"))
                .withMemoryCell(1000, Word.from(false, 0, 0, 0, 2, 0))
                .build();

        execute("DIV 1000", machine);

        assertThatMachine(machine)
                // b4 and b5 are undefined in the example
                .hasRegisterA("[+|0|,617|32|1]")
                // b4 is undefined in the example
                .hasRegisterX(Word.from(false, 0, 0, 0, 1, 1));
    }
}
