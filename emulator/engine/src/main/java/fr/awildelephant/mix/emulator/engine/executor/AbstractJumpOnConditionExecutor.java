package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

import java.util.function.Predicate;

public abstract class AbstractJumpOnConditionExecutor implements OperationExecutor, Predicate<Machine> {

    private final TwoBytesSigned address;

    public AbstractJumpOnConditionExecutor(TwoBytesSigned address) {
        this.address = address;
    }

    @Override
    public final void accept(Machine machine) {
        if (test(machine)) {
            new JMPExecutor(address).accept(machine);
        }
    }
}
