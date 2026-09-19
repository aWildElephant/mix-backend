package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.ComparisonIndicatorState.GREATER;

public final class JGExecutor extends AbstractJumpOnComparisonStateExecutor {

    public JGExecutor(TwoBytesSigned address) {
        super(address);
    }

    @Override
    public boolean test(ComparisonIndicator.ComparisonIndicatorState comparisonIndicatorState) {
        return comparisonIndicatorState == GREATER;
    }
}
