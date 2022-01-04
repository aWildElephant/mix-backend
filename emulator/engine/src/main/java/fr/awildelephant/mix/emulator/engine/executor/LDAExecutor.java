package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.modification.SetARegister;
import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.word.TwoBytesSignedMathService;
import fr.awildelephant.mix.emulator.word.Word;

public final class LDAExecutor extends AbstractSpecializedExecutor {

    public LDAExecutor(TwoBytesSignedMathService twoBytesSignedMathService, FieldSpecificationService fieldSpecificationService) {
        super(twoBytesSignedMathService, fieldSpecificationService);
    }

    @Override
    public StateModification apply(Machine machine, Instruction instruction) {
        final Address address = indexingProcess(machine, instruction);
        final Word memoryValue = machine.getMemory().get(address);

        final Word newValue = applyFieldSpecification(memoryValue, instruction);

        return new SetARegister(newValue);
    }
}
