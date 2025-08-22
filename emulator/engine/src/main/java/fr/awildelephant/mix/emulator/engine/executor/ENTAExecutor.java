package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.MathUtils;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class ENTAExecutor extends AbstractOperationExecutor {

    private final TwoBytesSigned value;
    private final byte indexSpecification;

    public ENTAExecutor(final TwoBytesSigned value, final byte indexSpecification) {
        this.value = value;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        Word value = Word.from(this.value.sign(), 0, 0, 0, this.value.b1(), this.value.b2());
        final boolean isZero = value.b4() == 0 && value.b5() == 0;

        if (indexSpecification != 0) {
            final Word registerContent = getRegisterContent(machine, indexSpecification);

            if (isZero) {
                // Set value to the content of the register. Keep the sign of the instruction
                value.b1(registerContent.b1());
                value.b2(registerContent.b2());
                value.b3(registerContent.b3());
                value.b4(registerContent.b4());
                value.b5(registerContent.b5());
            } else {
                // Add the content of the register
                value = MathUtils.add(value, registerContent).result(); // There can be no overflow
            }
        }

        machine.registerA().content(value);
    }
}
