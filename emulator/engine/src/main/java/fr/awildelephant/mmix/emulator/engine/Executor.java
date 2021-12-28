package fr.awildelephant.mmix.emulator.engine;

import fr.awildelephant.mmix.emulator.engine.modification.SetARegister;
import fr.awildelephant.mmix.emulator.engine.modification.SetMemoryCell;
import fr.awildelephant.mmix.emulator.engine.modification.StateModification;
import fr.awildelephant.mmix.emulator.engine.state.Machine;
import fr.awildelephant.mmix.emulator.instruction.Address;
import fr.awildelephant.mmix.emulator.instruction.FieldSpecificationHelper;
import fr.awildelephant.mmix.emulator.instruction.Instruction;
import fr.awildelephant.mmix.emulator.word.Word;

import java.util.Set;
import java.util.function.BiFunction;

public final class Executor implements BiFunction<Machine, Instruction, Set<StateModification>> {

    @Override
    public Set<StateModification> apply(Machine machine, Instruction instruction) {
        return switch (instruction.getOperation()) {
            case LDA -> executeLDA(machine, instruction);
            case NOP -> Set.of();
            case STA -> executeSTA(machine, instruction);
            default -> throw new UnsupportedOperationException("Not yet implemented: " + instruction.getOperation());
        };
    }

    private Set<StateModification> executeLDA(Machine machine, Instruction instruction) {
        final Word memoryValue = machine.getMemory().get(instruction.getAddress());

        final Word newValue = FieldSpecificationHelper.applySpecification(instruction.getModification(), memoryValue);

        // TODO: indexing process; done for every instructions, so probably dans apply()

        return Set.of(new SetARegister(newValue));
    }

    private Set<StateModification> executeSTA(Machine machine, Instruction instruction) {
        // TODO: indexing process
        final Address memoryAddress = instruction.getAddress();
        final Word memoryValue = machine.getMemory().get(memoryAddress);
        final Word registerValue = machine.getARegister().getWord();

        final Word newValue = FieldSpecificationHelper.applySpecification(instruction.getModification(), registerValue, memoryValue);

        return Set.of(new SetMemoryCell(memoryAddress, newValue));
    }
}
