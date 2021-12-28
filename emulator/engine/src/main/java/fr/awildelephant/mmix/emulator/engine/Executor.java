package fr.awildelephant.mmix.emulator.engine;

import fr.awildelephant.mmix.emulator.engine.modification.SetARegister;
import fr.awildelephant.mmix.emulator.engine.modification.StateModification;
import fr.awildelephant.mmix.emulator.engine.state.Machine;
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
            default -> throw new UnsupportedOperationException("Not yet implemented: " + instruction.getOperation());
        };
    }

    private Set<StateModification> executeLDA(Machine machine, Instruction instruction) {
        final Word value = machine.getMemory().get(instruction.getAddress());

        final Word modifiedValue = FieldSpecificationHelper.applySpecification(instruction.getModification(), value);

        // TODO: indexing process; done for every instructions, so probably dans apply()

        return Set.of(new SetARegister(modifiedValue));
    }
}
