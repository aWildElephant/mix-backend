package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public abstract class AbstractCMPiOperator extends AbstractComparisonOperator {

    public AbstractCMPiOperator(FieldSpecification fieldSpecification, Address address) {
        super(fieldSpecification, address);
    }

    @Override
    public Word getWordValue(Machine machine) {
        final TwoBytesSigned value = getTwoBytesSignedValue(machine);
        return Word.from(value.sign(), 0, 0, 0, value.b1(), value.b2());
    }

    public abstract TwoBytesSigned getTwoBytesSignedValue(Machine machine);
}
