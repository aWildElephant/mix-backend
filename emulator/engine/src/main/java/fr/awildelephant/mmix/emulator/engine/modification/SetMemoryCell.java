package fr.awildelephant.mmix.emulator.engine.modification;

import fr.awildelephant.mmix.emulator.engine.state.Machine;
import fr.awildelephant.mmix.emulator.instruction.Address;
import fr.awildelephant.mmix.emulator.word.Word;
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
