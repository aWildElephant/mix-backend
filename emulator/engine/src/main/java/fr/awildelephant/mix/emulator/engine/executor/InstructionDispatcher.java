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
            case LD1N -> new LD1NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD2 -> new LD2Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD2N -> new LD2NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD3 -> new LD3Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD3N -> new LD3NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD4 -> new LD4Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD4N -> new LD4NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD5 -> new LD5Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD5N -> new LD5NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD6 -> new LD6Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD6N -> new LD6NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LDX -> new LDXExecutor(fieldSpecification, address, indexSpecification);
            case LDXN -> new LDXNExecutor(fieldSpecification, address, indexSpecification);
            case STA -> new STAExecutor(fieldSpecification, address, indexSpecification);
            case STX -> new STXExecutor(fieldSpecification, address, indexSpecification);
            default -> throw new UnsupportedOperationException("Not yet implemented: " + operation);
        };

        specializedExecutor.accept(machine);
    }
}
