package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class InstructionDispatcher implements Executor {

    private final LD1Executor ld1Executor;
    private final LDAExecutor ldaExecutor;
    private final LDXExecutor ldxExecutor;
    private final NOPExecutor nopExecutor;
    private final STAExecutor staExecutor;
    private final STXExecutor stxExecutor;

    @Override
    public StateModification apply(Machine machine, Instruction instruction) {
        final Executor specializedExecutor = switch (instruction.getOperation()) {
            case LDA -> ldaExecutor;
            case LD1 -> ld1Executor;
            case LDX -> ldxExecutor;
            case NOP -> nopExecutor;
            case STA -> staExecutor;
            case STX -> stxExecutor;
            default -> throw new UnsupportedOperationException("Not yet implemented: " + instruction.getOperation());
        };

        return specializedExecutor.apply(machine, instruction);
    }
}
