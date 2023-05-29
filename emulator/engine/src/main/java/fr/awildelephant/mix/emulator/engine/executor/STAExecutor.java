package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.word.Word;

public final class STAExecutor extends AbstractOperationExecutor {

    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public STAExecutor(FieldSpecificationService fieldSpecificationService, FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        super(fieldSpecificationService);
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        final Address memoryAddress = indexingProcess(machine, address, indexSpecification);
        final Word memoryValue = machine.memory().get(memoryAddress);
        final Word registerValue = machine.registerA().content();

        final Word newValue = fieldSpecificationService.store(fieldSpecification, registerValue, memoryValue);

        machine.memory().put(memoryAddress, newValue);
    }
}
