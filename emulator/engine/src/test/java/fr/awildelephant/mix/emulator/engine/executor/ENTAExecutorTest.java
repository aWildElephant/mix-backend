package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.helper.WordStringRepresentation;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.MachineBuilder;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import org.junit.jupiter.api.Test;

import static fr.awildelephant.mix.emulator.engine.helper.ExecutorTestHelper.execute;
import static fr.awildelephant.mix.emulator.engine.testutil.MachineAsserter.assertThatMachine;

class ENTAExecutorTest {

    /**
     * page 133 first straightforward example
     */
    @Test
    void test1() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(WordStringRepresentation.toWord("[-|1|2|3|4|5]"))
                .build();

        execute("ENTA 0", machine);

        assertThatMachine(machine).hasRegisterA("[+|0|0|0|0|0]");
    }

    /**
     * page 133 second example which I don't understand
     */
    @Test
    void test2() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(WordStringRepresentation.toWord("[-|1|2|3|4|5]"))
                .withI1Register(TwoBytesSigned.from(false, 6, 7))
                .build();

        execute("ENTA 0,1", machine);

        assertThatMachine(machine).hasRegisterA("[+|0|0|0|6|7]");
    }

    /**
     * page 133 I don't understand the second so I don't understand the third either
     */
    @Test
    void test3() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(WordStringRepresentation.toWord("[-|1|2|3|4|5]"))
                .withI1Register(TwoBytesSigned.from(true, 6, 7))
                .build();

        execute("ENTA -0,1", machine);

        assertThatMachine(machine).hasRegisterA("[-|0|0|0|6|7]");
    }
}
