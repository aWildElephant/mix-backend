package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

/**
 * Same as JMP except that the contents of rJ are unchanged
 */
public final class JSJExecutor implements OperationExecutor {

    private final TwoBytesSigned address;

    public JSJExecutor(TwoBytesSigned address) {
        this.address = address;
    }

    @Override
    public void accept(Machine machine) {
        // Execute instruction at the given index next
        machine.metaState().setInstructionPointer(address.toInt());
    }
}
