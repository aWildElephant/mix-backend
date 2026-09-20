package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

public abstract class AbstractJumpOnComparisonStateExecutor extends AbstractJumpOnConditionExecutor {

    public AbstractJumpOnComparisonStateExecutor(TwoBytesSigned address) {
        super(address);
    }

    protected abstract boolean testComparisonIndicatorState(ComparisonIndicator.State comparisonIndicatorState);

    @Override
    public boolean test(Machine machine) {
        return testComparisonIndicatorState(machine.comparisonIndicator().state());
    }
}
