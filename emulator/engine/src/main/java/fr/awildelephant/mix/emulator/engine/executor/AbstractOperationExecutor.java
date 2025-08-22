package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.SignedTwoBytesRegister;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.word.Word;

public abstract class AbstractOperationExecutor implements OperationExecutor {

    protected Address indexingProcess(Machine machine, Address address, byte indexSpecification) {
        return switch (indexSpecification) {
            case 0 -> address;
            case 1 -> addRegisterContent(address, machine.registerI1());
            case 2 -> addRegisterContent(address, machine.registerI2());
            case 3 -> addRegisterContent(address, machine.registerI3());
            case 4 -> addRegisterContent(address, machine.registerI4());
            case 5 -> addRegisterContent(address, machine.registerI5());
            case 6 -> addRegisterContent(address, machine.registerI6());
            default -> throw new UnsupportedOperationException("Invalid index specification field " + indexSpecification);
        };
    }

    private Address addRegisterContent(Address address, SignedTwoBytesRegister indexRegister) {
        return new Address(address.value().add(indexRegister.content()));
    }

    protected Word getRegisterContent(final Machine machine, final byte indexSpecification) {
        final SignedTwoBytesRegister register = switch (indexSpecification) {
            case 1 -> machine.registerI1();
            case 2 -> machine.registerI2();
            case 3 -> machine.registerI3();
            case 4 -> machine.registerI4();
            case 5 -> machine.registerI5();
            case 6 -> machine.registerI6();
            default ->
                    throw new UnsupportedOperationException("Invalid index specification field " + indexSpecification);
        };

        return register.content().toWord();
    }
}
