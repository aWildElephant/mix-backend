package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.modification.SetXRegister;
import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.word.Word;

public final class LDXExecutor extends AbstractSpecializedExecutor{

    public LDXExecutor(FieldSpecificationService fieldSpecificationService) {
        super(fieldSpecificationService);
    }

    @Override
    public StateModification apply(Machine machine, Instruction instruction) {
        final Word memoryValue = machine.getMemory().get(instruction.getAddress());

        final Word newValue = applyFieldSpecification(memoryValue, instruction);

        // TODO: indexing process; done for every instructions, so probably dans apply()

        return new SetXRegister(newValue);
    }
}
