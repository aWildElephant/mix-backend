package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.SignedTwoBytesRegister;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;

public final class ST5Executor extends STiExecutor {

    public ST5Executor(FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        super(fieldSpecification, address, indexSpecification);
    }

    @Override
    SignedTwoBytesRegister register(Machine machine) {
        return machine.registerI5();
    }
}
