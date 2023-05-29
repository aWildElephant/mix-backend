package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.Word;

public final class LDAExecutor extends AbstractOperationExecutor {

    private final byte indexSpecification;
    private final FieldSpecification fieldSpecification;
    private final Address address;

    public LDAExecutor(Address address, byte indexSpecification, FieldSpecification fieldSpecification) {
        this.address = address;
        this.indexSpecification = indexSpecification;
        this.fieldSpecification = fieldSpecification;
    }

    @Override
    public void accept(Machine machine) {
        final Word memoryValue = machine.memory().get(indexingProcess(machine, address, indexSpecification));

        final Word newValue = fieldSpecification.load(memoryValue);

        machine.registerA().content(newValue);
    }
}
