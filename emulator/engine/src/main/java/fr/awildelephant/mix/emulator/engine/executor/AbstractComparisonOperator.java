package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.comparison.ComparisonUtils;
import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.Word;

import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.EQUAL;

public abstract class AbstractComparisonOperator implements OperationExecutor {

    protected final FieldSpecification fieldSpecification;
    protected final Address address;

    public AbstractComparisonOperator(FieldSpecification fieldSpecification, Address address) {
        this.fieldSpecification = fieldSpecification;
        this.address = address;
    }

    @Override
    public final void accept(Machine machine) {
        machine.comparisonIndicator().state(extractAndCompare(machine));
    }

    public abstract Word getWordValue(Machine machine);

    private ComparisonIndicator.State extractAndCompare(Machine machine) {
        if (fieldSpecification.left() == 0 && fieldSpecification.right() == 0) {
            return EQUAL;
        }
        final Word registerA = fieldSpecification.load(getWordValue(machine));
        final Word memoryValue = fieldSpecification.load(machine.memory().get(address));

        return ComparisonUtils.compare(registerA, memoryValue);
    }
}
