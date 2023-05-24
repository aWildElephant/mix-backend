package fr.awildelephant.mix.emulator.engine.modification;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

public record SetI1Register(TwoBytesSigned newValue) implements StateModification {

    @Override
    public void accept(Machine machine) {
        machine.registerI1().content(newValue);
    }
}
