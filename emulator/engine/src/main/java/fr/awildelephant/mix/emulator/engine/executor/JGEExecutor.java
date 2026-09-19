package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.EQUAL;
import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.GREATER;

public final class JGEExecutor extends AbstractJumpOnComparisonStateExecutor {

    public JGEExecutor(TwoBytesSigned address) {
        super(address);
    }

    @Override
    public boolean test(ComparisonIndicator.State comparisonIndicatorState) {
        return comparisonIndicatorState == EQUAL || comparisonIndicatorState == GREATER;
    }
}
