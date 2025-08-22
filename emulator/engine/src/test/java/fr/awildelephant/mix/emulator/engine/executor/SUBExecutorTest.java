package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.helper.WordStringRepresentation;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.MachineBuilder;
import org.junit.jupiter.api.Test;

import static fr.awildelephant.mix.emulator.engine.helper.ExecutorTestHelper.execute;
import static fr.awildelephant.mix.emulator.engine.testutil.MachineAsserter.assertThatMachine;

class SUBExecutorTest {

    /**
     * page 132 SUB example
     */
    @Test
    void test1() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(WordStringRepresentation.toWord("[-|,1234|0|0|9]"))
                .withMemoryCell(1000, WordStringRepresentation.toWord("[-|,2000|,150|0]"))
                .build();

        execute("SUB 1000", machine);

        // last value is actually undefined in the example, but we produce a deterministic result
        assertThatMachine(machine).hasRegisterA("[+|,766|,149|55]");
    }
}
