package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.Word;

public final class CMPXExecutor extends AbstractComparisonOperator {

    public CMPXExecutor(FieldSpecification fieldSpecification, Address address) {
        super(fieldSpecification, address);
    }

    @Override
    public Word getWordValue(Machine machine) {
        return machine.registerX().content();
    }
}
