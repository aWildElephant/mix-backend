package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.SignedTwoBytesRegister;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.word.ByteHelper;
import fr.awildelephant.mix.emulator.word.TwoBytesSignedMathService;

public abstract class AbstractSpecializedExecutor implements Executor {

    private final TwoBytesSignedMathService mathService;
    protected final FieldSpecificationService fieldSpecificationService;

    protected AbstractSpecializedExecutor(TwoBytesSignedMathService mathService, FieldSpecificationService fieldSpecificationService) {
        this.mathService = mathService;
        this.fieldSpecificationService = fieldSpecificationService;
    }

    protected Address indexingProcess(Machine machine, Instruction instruction) {
        final byte index = instruction.indexSpecification();

        return switch (index) {
            case ByteHelper.b0 -> instruction.address();
            case ByteHelper.b1 -> addRegisterContent(instruction.address(), machine.registerI1());
            case ByteHelper.b2 -> addRegisterContent(instruction.address(), machine.registerI2());
            case ByteHelper.b3 -> addRegisterContent(instruction.address(), machine.registerI3());
            case ByteHelper.b4 -> addRegisterContent(instruction.address(), machine.registerI4());
            case ByteHelper.b5 -> addRegisterContent(instruction.address(), machine.registerI5());
            case ByteHelper.b6 -> addRegisterContent(instruction.address(), machine.registerI6());
            default -> throw new UnsupportedOperationException("Invalid index specification field " + index);
        };
    }

    private Address addRegisterContent(Address address, SignedTwoBytesRegister indexRegister) {
        return new Address(mathService.add(address.value(), indexRegister.content()));
    }

    protected FieldSpecification fieldSpecification(Instruction instruction) {
        return instruction.modification().toFieldSpecification();
    }
}
