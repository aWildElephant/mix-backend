package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class STJExecutor extends AbstractOperationExecutor {

    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public STJExecutor(FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        final Address memoryAddress = indexingProcess(machine, address, indexSpecification);
        final Word memoryValue = machine.memory().get(memoryAddress);
        final TwoBytesSigned registerValue = machine.registerJ().content();
        final Word realValue = Word.from(true, 0, 0, 0, registerValue.b1(), registerValue.b2());

        final Word newValue = fieldSpecification.store(realValue, memoryValue);

        machine.memory().put(memoryAddress, newValue);
    }
}
