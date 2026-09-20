package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class SRAExecutor implements OperationExecutor {

    private final TwoBytesSigned value;

    public SRAExecutor(TwoBytesSigned value) {
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
            b1 = registerA.b2();
            b2 = registerA.b3();
            b3 = registerA.b4();
            b4 = registerA.b5();
            b5 = 0;
        } else if (numberOfBytesToShift == 2) {
            b1 = registerA.b3();
            b2 = registerA.b4();
            b3 = registerA.b5();
            b4 = 0;
            b5 = 0;
        } else if (numberOfBytesToShift == 3) {
            b1 = registerA.b4();
            b2 = registerA.b5();
            b3 = 0;
            b4 = 0;
            b5 = 0;
        } else if (numberOfBytesToShift == 4) {
            b1 = registerA.b5();
            b2 = 0;
            b3 = 0;
            b4 = 0;
            b5 = 0;
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
