package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.instruction.Operation;
import fr.awildelephant.mix.emulator.word.WordService;

import java.util.function.BiConsumer;

public final class InstructionDispatcher implements BiConsumer<Machine, Instruction> {

    private final WordService wordService;

    public InstructionDispatcher(WordService wordService) {
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
            case LDA -> new LDAExecutor(address, indexSpecification, fieldSpecification);
            case LDAN -> new LDANExecutor(fieldSpecification, address, indexSpecification);
            case LD1 -> new LD1Executor(wordService, fieldSpecification, address, indexSpecification);
            case LDX -> new LDXExecutor(fieldSpecification, address, indexSpecification);
            case STA -> new STAExecutor(fieldSpecification, address, indexSpecification);
            case STX -> new STXExecutor(fieldSpecification, address, indexSpecification);
            default -> throw new UnsupportedOperationException("Not yet implemented: " + operation);
        };

        specializedExecutor.accept(machine);
    }
}
