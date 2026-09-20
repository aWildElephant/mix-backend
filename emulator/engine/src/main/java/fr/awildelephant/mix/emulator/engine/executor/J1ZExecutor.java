package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.SignedTwoBytesRegister;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

public final class J1ZExecutor extends JiExecutor {

    public J1ZExecutor(TwoBytesSigned address) {
        super(address);
    }

    @Override
    protected SignedTwoBytesRegister getRegister(Machine machine) {
        return machine.registerI1();
    }

    @Override
    protected boolean doJump(int comparisonResult) {
        return comparisonResult == 0;
    }
}
