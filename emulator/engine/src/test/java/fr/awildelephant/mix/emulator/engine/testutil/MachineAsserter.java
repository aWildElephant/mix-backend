package fr.awildelephant.mix.emulator.engine.testutil;

import fr.awildelephant.mix.emulator.engine.helper.WordStringRepresentation;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.Word;

import static org.assertj.core.api.Assertions.assertThat;

public final class MachineAsserter {

    private final Machine machine;

    private MachineAsserter(final Machine machine) {
        this.machine = machine;
    }

    public static MachineAsserter assertThatMachine(final Machine machine) {
        return new MachineAsserter(machine);
    }

    public MachineAsserter hasRegisterA(final String expectedStringRepresentation) {
        return hasRegisterA(WordStringRepresentation.toWord(expectedStringRepresentation));
    }

    public MachineAsserter hasRegisterA(final Word expected) {
        assertThat(machine.registerA().content()).isEqualTo(expected);
        return this;
    }

    public MachineAsserter hasRegisterX(final String expectedStringRepresentation) {
        return hasRegisterX(WordStringRepresentation.toWord(expectedStringRepresentation));
    }

    public MachineAsserter hasRegisterX(final Word expected) {
        assertThat(machine.registerX().content()).isEqualTo(expected);
        return this;
    }

    public MachineAsserter hasOverflowToggleNotSet() {
        if (machine.overflowToggle().state()) {
            throw new AssertionError("Overflow toggle is set");
        }
        return this;
    }
}
