package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.Word;

public class LDXNExecutor extends AbstractOperationExecutor {

    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public LDXNExecutor(FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        final Word memoryValue = machine.memory().get(indexingProcess(machine, address, indexSpecification));
        final Word newValue = fieldSpecification.load(memoryValue);
        newValue.negate();
        machine.registerX().content(newValue);
    }
}
