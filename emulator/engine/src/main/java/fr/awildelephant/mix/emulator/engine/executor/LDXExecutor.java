package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.word.TwoBytesSignedMathService;
import fr.awildelephant.mix.emulator.word.Word;

public final class LDXExecutor extends AbstractOperationExecutor {

    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public LDXExecutor(TwoBytesSignedMathService twoBytesSignedMathService, FieldSpecificationService fieldSpecificationService, FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        super(twoBytesSignedMathService, fieldSpecificationService);
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        final Word memoryValue = machine.memory().get(indexingProcess(machine, address, indexSpecification));

        final Word newValue = fieldSpecificationService.load(fieldSpecification, memoryValue);

        machine.registerX().content(newValue);
    }
}
