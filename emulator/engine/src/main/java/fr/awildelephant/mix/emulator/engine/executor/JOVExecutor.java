package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.OverflowToggle;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

/**
 * If the overflow toggle is on, it is turned off and a JMP occurs; otherwise nothing happens.
 */
public final class JOVExecutor implements OperationExecutor {

    private final TwoBytesSigned address;

    public JOVExecutor(TwoBytesSigned address) {
        this.address = address;
    }

    @Override
    public void accept(Machine machine) {
        final OverflowToggle overflowToggle = machine.overflowToggle();
        if (overflowToggle.state()) {
            new JMPExecutor(address).accept(machine);

            overflowToggle.clear();
        }
    }
}
