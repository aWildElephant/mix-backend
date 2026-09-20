package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class SLAExecutor implements OperationExecutor {

    private final TwoBytesSigned value;

    public SLAExecutor(TwoBytesSigned value) {
        this.value = value;
    }

    @Override
    public void accept(Machine machine) {
        final Word registerA = machine.registerA().content();

        final int numberOfBytesToShift = value.toInt();

        if (numberOfBytesToShift <= 0) {
            return; // Assume this is fine and do nothing
        }

        final int b1, b2, b3, b4, b5;
        if (numberOfBytesToShift == 1) {
            b1 = 0;
            b2 = registerA.b1();
            b3 = registerA.b2();
            b4 = registerA.b3();
            b5 = registerA.b4();
        } else if (numberOfBytesToShift == 2) {
            b1 = 0;
            b2 = 0;
            b3 = registerA.b1();
            b4 = registerA.b2();
            b5 = registerA.b3();
        } else if (numberOfBytesToShift == 3) {
            b1 = 0;
            b2 = 0;
            b3 = 0;
            b4 = registerA.b1();
            b5 = registerA.b2();
        } else if (numberOfBytesToShift == 4) {
            b1 = 0;
            b2 = 0;
            b3 = 0;
            b4 = 0;
            b5 = registerA.b1();
        } else {
            b1 = 0;
            b2 = 0;
            b3 = 0;
            b4 = 0;
            b5 = 0;
        }

        machine.registerA().content(Word.from(registerA.sign(), b1, b2, b3, b4, b5));
    }
}
