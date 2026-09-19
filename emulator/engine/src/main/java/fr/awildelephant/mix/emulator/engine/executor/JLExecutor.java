package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.LESS;

public final class JLExecutor extends AbstractJumpOnComparisonStateExecutor {

    public JLExecutor(TwoBytesSigned address) {
        super(address);
    }

    @Override
    public boolean test(ComparisonIndicator.State comparisonIndicatorState) {
        return comparisonIndicatorState == LESS;
    }
}
