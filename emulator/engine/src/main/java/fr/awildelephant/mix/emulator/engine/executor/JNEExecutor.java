package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.GREATER;
import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.LESS;

public final class JNEExecutor extends AbstractJumpOnComparisonStateExecutor {

    public JNEExecutor(TwoBytesSigned address) {
        super(address);
    }

    @Override
    public boolean testComparisonIndicatorState(ComparisonIndicator.State comparisonIndicatorState) {
        return comparisonIndicatorState == LESS || comparisonIndicatorState == GREATER;
    }
}
