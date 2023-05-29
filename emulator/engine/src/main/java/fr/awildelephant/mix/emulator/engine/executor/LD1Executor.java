package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.TwoBytesSignedMathService;
import fr.awildelephant.mix.emulator.word.Word;
import fr.awildelephant.mix.emulator.word.WordService;

public final class LD1Executor extends AbstractOperationExecutor {

    private final WordService wordService;
    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public LD1Executor(TwoBytesSignedMathService twoBytesSignedMathService, FieldSpecificationService fieldSpecificationService, WordService wordService, FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        super(twoBytesSignedMathService, fieldSpecificationService);

        this.wordService = wordService;
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        // FIXME: probably not correct
        final Address memoryAddress = indexingProcess(machine, address, indexSpecification);
        final Word memoryValue = machine.memory().get(memoryAddress);

        final TwoBytesSigned newValue = wordService.extract(fieldSpecificationService.load(fieldSpecification, memoryValue));

        machine.registerI1().content(newValue);
    }
}
