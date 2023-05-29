package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.SignedTwoBytesRegister;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.word.ByteHelper;

public abstract class AbstractOperationExecutor implements OperationExecutor {

    protected final FieldSpecificationService fieldSpecificationService;

    protected AbstractOperationExecutor(FieldSpecificationService fieldSpecificationService) {
        this.fieldSpecificationService = fieldSpecificationService;
    }

    protected Address indexingProcess(Machine machine, Address address, byte indexSpecification) {
        return switch (indexSpecification) {
            case ByteHelper.b0 -> address;
            case ByteHelper.b1 -> addRegisterContent(address, machine.registerI1());
            case ByteHelper.b2 -> addRegisterContent(address, machine.registerI2());
            case ByteHelper.b3 -> addRegisterContent(address, machine.registerI3());
            case ByteHelper.b4 -> addRegisterContent(address, machine.registerI4());
            case ByteHelper.b5 -> addRegisterContent(address, machine.registerI5());
            case ByteHelper.b6 -> addRegisterContent(address, machine.registerI6());
            default -> throw new UnsupportedOperationException("Invalid index specification field " + indexSpecification);
        };
    }

    private Address addRegisterContent(Address address, SignedTwoBytesRegister indexRegister) {
        return new Address(address.value().add(indexRegister.content()));
    }
}
