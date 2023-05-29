package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.instruction.Operation;
import fr.awildelephant.mix.emulator.word.TwoBytesSignedMathService;
import fr.awildelephant.mix.emulator.word.WordService;

import java.util.function.BiConsumer;

public final class InstructionDispatcher implements BiConsumer<Machine, Instruction> {

    private final FieldSpecificationService fieldSpecificationService;
    private final TwoBytesSignedMathService twoBytesSignedMathService;
    private final WordService wordService;

    public InstructionDispatcher(FieldSpecificationService fieldSpecificationService, TwoBytesSignedMathService twoBytesSignedMathService, WordService wordService) {
        this.fieldSpecificationService = fieldSpecificationService;
        this.twoBytesSignedMathService = twoBytesSignedMathService;
        this.wordService = wordService;
    }

    @Override
    public void accept(Machine machine, Instruction instruction) {
        final Operation operation = instruction.operation();

        if (operation == Operation.NOP) {
            return;
        }

        final Address address = instruction.address();
        final byte indexSpecification = instruction.indexSpecification();
        final FieldSpecification fieldSpecification = instruction.modification().toFieldSpecification();

        final OperationExecutor specializedExecutor = switch (operation) {
            case LDA -> new LDAExecutor(twoBytesSignedMathService, fieldSpecificationService, address, indexSpecification, fieldSpecification);
            case LDAN -> new LDANExecutor(twoBytesSignedMathService, fieldSpecificationService, fieldSpecification, address, indexSpecification);
            case LD1 -> new LD1Executor(twoBytesSignedMathService, fieldSpecificationService, wordService, fieldSpecification, address, indexSpecification);
            case LDX -> new LDXExecutor(twoBytesSignedMathService, fieldSpecificationService, fieldSpecification, address, indexSpecification);
            case STA -> new STAExecutor(twoBytesSignedMathService, fieldSpecificationService, fieldSpecification, address, indexSpecification);
            case STX -> new STXExecutor(twoBytesSignedMathService, fieldSpecificationService, fieldSpecification, address, indexSpecification);
            default -> throw new UnsupportedOperationException("Not yet implemented: " + operation);
        };

        specializedExecutor.accept(machine);
    }
}
