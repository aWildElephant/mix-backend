package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.OverflowToggle;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

/**
 * If the overflow toggle is off, a JMP occurs; otherwise it is turned off.
 */
public final class JNOVExecutor implements OperationExecutor {

    private final TwoBytesSigned address;

    public JNOVExecutor(TwoBytesSigned address) {
        this.address = address;
    }

    @Override
    public void accept(Machine machine) {
        final OverflowToggle overflowToggle = machine.overflowToggle();
        if (!overflowToggle.state()) {
            new JMPExecutor(address).accept(machine);
        } else {
            overflowToggle.clear();
        }
    }
}
