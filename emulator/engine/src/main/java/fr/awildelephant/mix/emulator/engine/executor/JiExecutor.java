package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.comparison.ComparisonUtils;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.SignedTwoBytesRegister;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

public abstract class JiExecutor implements OperationExecutor {

    private final TwoBytesSigned address;

    public JiExecutor(TwoBytesSigned address) {
        this.address = address;
    }

    protected abstract SignedTwoBytesRegister getRegister(Machine machine);

    protected abstract boolean doJump(int comparisonResult);

    @Override
    public final void accept(Machine machine) {
        final SignedTwoBytesRegister register = getRegister(machine);

        final int result = ComparisonUtils.getTwoBytesSignedComparator().compare(register.content(), TwoBytesSigned.zero());

        if (doJump(result)) {
            new JMPExecutor(address).accept(machine);
        }
    }
}
