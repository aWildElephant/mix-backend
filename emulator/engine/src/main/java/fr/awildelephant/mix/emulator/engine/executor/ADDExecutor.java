package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.ComputationResult;
import fr.awildelephant.mix.emulator.word.MathUtils;
import fr.awildelephant.mix.emulator.word.Word;

public final class ADDExecutor extends AbstractOperationExecutor {

    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public ADDExecutor(FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        final Word memoryValue = machine.memory().get(indexingProcess(machine, address, indexSpecification));

        final Word newValue = fieldSpecification.load(memoryValue);

        final Word registerAContent = machine.registerA().content();

        final ComputationResult<Word> computation = MathUtils.add(registerAContent, newValue);

        machine.registerA().content(computation.result());
        if (computation.overflow()) {
            machine.overflowToggle().set();
        }
    }
}
