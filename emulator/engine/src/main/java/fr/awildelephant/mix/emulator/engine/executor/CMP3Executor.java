package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;

public final class CMP3Executor extends AbstractCMPiOperator {

    public CMP3Executor(FieldSpecification fieldSpecification, Address address) {
        super(fieldSpecification, address);
    }

    @Override
    public TwoBytesSigned getTwoBytesSignedValue(Machine machine) {
        return machine.registerI3().content();
    }
}
