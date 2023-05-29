package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.modification.SetARegister;
import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.word.TwoBytesSignedMathService;
import fr.awildelephant.mix.emulator.word.Word;

public final class LDANExecutor extends AbstractSpecializedExecutor {

    public LDANExecutor(TwoBytesSignedMathService mathService, FieldSpecificationService fieldSpecificationService) {
        super(mathService, fieldSpecificationService);
    }

    @Override
    public StateModification apply(Machine machine, Instruction instruction) {
        final Address address = indexingProcess(machine, instruction);
        final Word memoryValue = machine.memory().get(address);

        final Word newValue = fieldSpecificationService.load(fieldSpecification(instruction), memoryValue);

        return new SetARegister(newValue.negate());
    }
}
