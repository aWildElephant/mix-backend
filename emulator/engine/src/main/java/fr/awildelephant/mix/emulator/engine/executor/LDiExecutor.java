package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.SignedTwoBytesRegister;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;
import fr.awildelephant.mix.emulator.word.WordService;

import java.util.function.Function;

public abstract class LDiExecutor extends AbstractOperationExecutor {

    private final WordService wordService;
    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;
    private final Function<Machine, SignedTwoBytesRegister> registerGetter;

    public LDiExecutor(WordService wordService, FieldSpecification fieldSpecification, Address address, byte indexSpecification, Function<Machine, SignedTwoBytesRegister> registerGetter) {
        this.wordService = wordService;
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
        this.registerGetter = registerGetter;
    }

    TwoBytesSigned extract(Machine machine) {
        final Address memoryAddress = indexingProcess(machine, address, indexSpecification);
        final Word memoryValue = machine.memory().get(memoryAddress);

        return wordService.extract(fieldSpecification.load(memoryValue));
    }

    @Override
    public void accept(Machine machine) {
        registerGetter.apply(machine).content(extract(machine));
    }
}
