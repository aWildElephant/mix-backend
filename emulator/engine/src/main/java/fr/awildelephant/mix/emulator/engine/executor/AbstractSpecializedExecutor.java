package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.SignedTwoBytesRegister;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.word.ByteHelper;
import fr.awildelephant.mix.emulator.word.TwoBytesSignedMathService;
import fr.awildelephant.mix.emulator.word.Word;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractSpecializedExecutor implements Executor {

    private final TwoBytesSignedMathService mathService;
    private final FieldSpecificationService fieldSpecificationService;

    protected Address indexingProcess(Machine machine, Instruction instruction) {
        final byte index = instruction.indexSpecification();

        return switch (index) {
            case ByteHelper.b0 -> instruction.address();
            case ByteHelper.b1 -> addRegisterContent(instruction.address(), machine.getRegisterI1());
            case ByteHelper.b2 -> addRegisterContent(instruction.address(), machine.getRegisterI2());
            case ByteHelper.b3 -> addRegisterContent(instruction.address(), machine.getRegisterI3());
            case ByteHelper.b4 -> addRegisterContent(instruction.address(), machine.getRegisterI4());
            case ByteHelper.b5 -> addRegisterContent(instruction.address(), machine.getRegisterI5());
            case ByteHelper.b6 -> addRegisterContent(instruction.address(), machine.getRegisterI6());
            default -> throw new UnsupportedOperationException("Invalid index specification field " + index);
        };
    }

    private Address addRegisterContent(Address address, SignedTwoBytesRegister indexRegister) {
        return new Address(mathService.add(address.value(), indexRegister.getContent()));
    }

    protected Word applyFieldSpecification(Word value, Instruction instruction) {
        return fieldSpecificationService.applySpecification(instruction.modification().toFieldSpecification(), value);
    }

    protected Word applyFieldSpecification(Word newValue, Word originalValue, Instruction instruction) {
        return fieldSpecificationService.applySpecification(instruction.modification().toFieldSpecification(), newValue, originalValue);
    }
}
