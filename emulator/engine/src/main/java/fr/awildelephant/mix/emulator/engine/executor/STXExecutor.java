package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.Word;

public final class STXExecutor extends AbstractOperationExecutor {

    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public STXExecutor(FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        final Address memoryAddress = indexingProcess(machine, address, indexSpecification);
        final Word memoryValue = machine.memory().get(memoryAddress);
        final Word registerValue = machine.registerX().content();

        final Word newValue = fieldSpecification.store(registerValue, memoryValue);

        machine.memory().put(memoryAddress, newValue);
    }
}
