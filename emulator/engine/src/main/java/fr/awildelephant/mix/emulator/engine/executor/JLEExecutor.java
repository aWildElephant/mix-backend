package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.EQUAL;
import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.LESS;

public final class JLEExecutor extends AbstractJumpOnComparisonStateExecutor {

    public JLEExecutor(TwoBytesSigned address) {
        super(address);
    }

    @Override
    public boolean testComparisonIndicatorState(ComparisonIndicator.State comparisonIndicatorState) {
        return comparisonIndicatorState == LESS || comparisonIndicatorState == EQUAL;
    }
}
