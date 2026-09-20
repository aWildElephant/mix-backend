package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.comparison.ComparisonUtils;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class JXZExecutor extends AbstractJumpOnConditionExecutor {

    public JXZExecutor(TwoBytesSigned address) {
        super(address);
    }

    @Override
    public boolean test(Machine machine) {
        return ComparisonUtils.getWordComparator().compare(machine.registerX().content(), Word.zero()) == 0;
    }
}
