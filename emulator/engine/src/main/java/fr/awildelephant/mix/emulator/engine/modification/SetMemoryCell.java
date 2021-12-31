package fr.awildelephant.mix.emulator.engine.modification;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.word.Word;
import lombok.Value;

@Value
public class SetMemoryCell implements StateModification {

    Address memoryAddress;
    Word newValue;

    @Override
    public void accept(Machine machine) {
        machine.getMemory().put(memoryAddress, newValue);
    }
}
