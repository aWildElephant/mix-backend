package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.ComparisonIndicatorState.GREATER;
import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.ComparisonIndicatorState.LESS;

public final class JNEExecutor extends AbstractJumpOnComparisonStateExecutor {

    public JNEExecutor(TwoBytesSigned address) {
        super(address);
    }

    @Override
    public boolean test(ComparisonIndicator.ComparisonIndicatorState comparisonIndicatorState) {
        return comparisonIndicatorState == LESS || comparisonIndicatorState == GREATER;
    }
}
