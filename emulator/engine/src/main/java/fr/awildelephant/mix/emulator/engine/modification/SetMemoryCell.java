package fr.awildelephant.mix.emulator.engine.modification;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.word.Word;

public record SetMemoryCell(Address memoryAddress, Word newValue) implements StateModification {

    @Override
    public void accept(Machine machine) {
        machine.memory().put(memoryAddress, newValue);
    }
}
