package fr.awildelephant.mix.emulator.engine;

import fr.awildelephant.mix.emulator.engine.modification.*;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;
import fr.awildelephant.mix.emulator.word.WordService;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public final class Executor implements BiFunction<Machine, Instruction, Set<StateModification>> {

    private final FieldSpecificationService fieldSpecificationService;
    private final WordService wordService;

    @Override
    public Set<StateModification> apply(Machine machine, Instruction instruction) {
        return switch (instruction.getOperation()) {
            case LDA -> executeLDA(machine, instruction);
            case LD1 -> executeLD1(machine, instruction);
            case LDX -> executeLDX(machine, instruction);
            case NOP -> Set.of();
            case STA -> executeSTA(machine, instruction);
            case STX -> executeSTX(machine, instruction);
            default -> throw new UnsupportedOperationException("Not yet implemented: " + instruction.getOperation());
        };
    }

    private Set<StateModification> executeLDA(Machine machine, Instruction instruction) {
        final Word memoryValue = machine.getMemory().get(instruction.getAddress());

        final Word newValue = fieldSpecificationService.applySpecification(instruction.getModification(), memoryValue);

        // TODO: indexing process; done for every instructions, so probably dans apply()

        return Set.of(new SetARegister(newValue));
    }

    private Set<StateModification> executeLD1(Machine machine, Instruction instruction) {
        final Word memoryValue = machine.getMemory().get(instruction.getAddress());

        final TwoBytesSigned newValue = wordService.extract(
                fieldSpecificationService.applySpecification(instruction.getModification(), memoryValue));

        // TODO: indexing process

        return Set.of(new SetI1Register(newValue));
    }

    private Set<StateModification> executeLDX(Machine machine, Instruction instruction) {
        final Word memoryValue = machine.getMemory().get(instruction.getAddress());

        final Word newValue = fieldSpecificationService.applySpecification(instruction.getModification(), memoryValue);

        // TODO: indexing process; done for every instructions, so probably dans apply()

        return Set.of(new SetXRegister(newValue));
    }

    private Set<StateModification> executeSTA(Machine machine, Instruction instruction) {
        // TODO: indexing process
        final Address memoryAddress = instruction.getAddress();
        final Word memoryValue = machine.getMemory().get(memoryAddress);
        final Word registerValue = machine.getRegisterA().getWord();

        final Word newValue = fieldSpecificationService.applySpecification(instruction.getModification(), registerValue, memoryValue);

        return Set.of(new SetMemoryCell(memoryAddress, newValue));
    }

    private Set<StateModification> executeSTX(Machine machine, Instruction instruction) {
        // TODO: indexing process
        final Address memoryAddress = instruction.getAddress();
        final Word memoryValue = machine.getMemory().get(memoryAddress);
        final Word registerValue = machine.getRegisterX().getWord();

        final Word newValue = fieldSpecificationService.applySpecification(instruction.getModification(), registerValue, memoryValue);

        return Set.of(new SetMemoryCell(memoryAddress, newValue));
    }
}
