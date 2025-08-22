package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.helper.WordStringRepresentation;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.MachineBuilder;
import org.junit.jupiter.api.Test;

import static fr.awildelephant.mix.emulator.engine.helper.ExecutorTestHelper.execute;
import static fr.awildelephant.mix.emulator.engine.testutil.MachineAsserter.assertThatMachine;

class ADDExecutorTest {

    /**
     * page 132 ADD example
     */
    @Test
    void test1() {
        final Machine machine = MachineBuilder.builder()
                .withARegister(WordStringRepresentation.toWord("[+|,1234|1|,150]"))
                .withMemoryCell(1000, WordStringRepresentation.toWord("[+|,100|5|,50]"))
                .build();

        execute("ADD 1000", machine);

        assertThatMachine(machine).hasRegisterA("[+|,1334|6|,200]");
    }
}
