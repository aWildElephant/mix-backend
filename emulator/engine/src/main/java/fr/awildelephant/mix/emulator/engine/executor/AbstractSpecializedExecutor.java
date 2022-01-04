package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.engine.state.TwoBytesSignedRegister;
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
        final byte index = instruction.getIndexSpecification();

        return switch (index) {
            case ByteHelper.b0 -> instruction.getAddress();
            case ByteHelper.b1 -> addRegisterContent(instruction.getAddress(), machine.getRI1());
            default -> throw new UnsupportedOperationException("Invalid index specification value " + index);
        };
    }

    private Address addRegisterContent(Address address, TwoBytesSignedRegister indexRegister) {
        return new Address(mathService.add(address.value(), indexRegister.getContent()));
    }

    protected Word applyFieldSpecification(Word value, Instruction instruction) {
        return fieldSpecificationService.applySpecification(instruction.getModification(), value);
    }

    protected Word applyFieldSpecification(Word newValue, Word originalValue, Instruction instruction) {
        return fieldSpecificationService.applySpecification(instruction.getModification(), newValue, originalValue);
    }
}
