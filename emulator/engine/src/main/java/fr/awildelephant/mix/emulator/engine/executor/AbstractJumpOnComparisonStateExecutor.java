package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

import java.util.function.Predicate;

public abstract class AbstractJumpOnComparisonStateExecutor implements OperationExecutor, Predicate<ComparisonIndicator.ComparisonIndicatorState> {

    private final TwoBytesSigned address;

    public AbstractJumpOnComparisonStateExecutor(TwoBytesSigned address) {
        this.address = address;
    }

    @Override
    public final void accept(Machine machine) {
        if (test(machine.comparisonIndicator().state())) {
            new JMPExecutor(address).accept(machine);
        }
    }
}
