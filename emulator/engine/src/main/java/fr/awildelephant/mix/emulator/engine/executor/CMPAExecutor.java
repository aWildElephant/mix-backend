package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.Word;
import fr.awildelephant.mix.emulator.word.WordHelper;

import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.EQUAL;
import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.GREATER;
import static fr.awildelephant.mix.emulator.engine.state.ComparisonIndicator.State.LESS;

public final class CMPAExecutor implements OperationExecutor {

    private final FieldSpecification fieldSpecification;
    private final Address address;

    public CMPAExecutor(FieldSpecification fieldSpecification, Address address) {
        this.fieldSpecification = fieldSpecification;
        this.address = address;
    }

    @Override
    public void accept(Machine machine) {
        machine.comparisonIndicator().state(extractAndCompare(machine));
    }

    private ComparisonIndicator.State extractAndCompare(Machine machine) {
        if (fieldSpecification.left() == 0 && fieldSpecification.right() == 0) {
            return EQUAL;
        }
        final Word registerA = fieldSpecification.load(machine.registerA().content());
        final Word memoryValue = fieldSpecification.load(machine.memory().get(address));

        final int comparison = WordHelper.toInt(registerA) - WordHelper.toInt(memoryValue);
        if (comparison > 0) {
            return GREATER;
        } else if (comparison < 0) {
            return LESS;
        } else {
            return EQUAL;
        }
    }
}
