package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.comparison.ComparisonUtils;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class JANNExecutor extends AbstractJumpOnConditionExecutor {

    public JANNExecutor(TwoBytesSigned address) {
        super(address);
    }

    @Override
    public boolean test(Machine machine) {
        return ComparisonUtils.getWordComparator().compare(machine.registerA().content(), Word.zero()) >= 0;
    }
}
