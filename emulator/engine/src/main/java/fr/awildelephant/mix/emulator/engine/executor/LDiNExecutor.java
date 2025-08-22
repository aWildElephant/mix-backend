package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.WordService;

public abstract class LDiNExecutor extends LDiExecutor {

    public LDiNExecutor(WordService wordService, FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        super(wordService, fieldSpecification, address, indexSpecification);
    }

    @Override
    TwoBytesSigned extract(Machine machine) {
        final TwoBytesSigned value = super.extract(machine);
        value.negate();
        return value;
    }
}
