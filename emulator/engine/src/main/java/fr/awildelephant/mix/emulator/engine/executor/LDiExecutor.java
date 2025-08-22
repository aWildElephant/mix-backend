package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.SignedTwoBytesRegister;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;
import fr.awildelephant.mix.emulator.word.WordService;

public abstract class LDiExecutor extends AbstractOperationExecutor {

    private final WordService wordService;
    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public LDiExecutor(WordService wordService, FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        this.wordService = wordService;
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    TwoBytesSigned extract(Machine machine) {
        final Address memoryAddress = indexingProcess(machine, address, indexSpecification);
        final Word memoryValue = machine.memory().get(memoryAddress);

        return wordService.extract(fieldSpecification.load(memoryValue));
    }

    protected abstract SignedTwoBytesRegister register(Machine machine);

    @Override
    public void accept(Machine machine) {
        register(machine).content(extract(machine));
    }
}
