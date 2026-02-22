package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.ComputationResult;
import fr.awildelephant.mix.emulator.word.MathUtils;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class INCAExecutor extends AbstractOperationExecutor {

    private final TwoBytesSigned value;

    public INCAExecutor(TwoBytesSigned value) {
        this.value = value;
    }

    @Override
    public void accept(Machine machine) {
        final Word registerAContent = machine.registerA().content();

        final ComputationResult<Word> computation = MathUtils.add(registerAContent, value.toWord());

        machine.registerA().content(computation.result());
        if (computation.overflow()) {
            machine.overflowToggle().set();
        }
    }
}
