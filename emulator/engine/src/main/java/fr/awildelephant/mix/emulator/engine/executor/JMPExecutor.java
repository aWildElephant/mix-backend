package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

/**
 * Unconditional jump; The next instruction is taken from location M.
 */
public class JMPExecutor implements OperationExecutor {

    private final TwoBytesSigned address;

    public JMPExecutor(TwoBytesSigned address) {
        this.address = address;
    }

    @Override
    public void accept(Machine machine) {
        // Execute instruction at the given index next
        machine.metaState().setInstructionPointer(address.toInt());

        // Set register J
        machine.registerJ().content(address);
    }
}
