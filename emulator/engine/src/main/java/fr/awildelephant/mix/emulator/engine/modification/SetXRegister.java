package fr.awildelephant.mix.emulator.engine.modification;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.Word;

public record SetXRegister(Word newValue) implements StateModification {

    @Override
    public void accept(Machine machine) {
        machine.registerX().content(newValue);
    }
}
