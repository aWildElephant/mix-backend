package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Instruction;

public final class InstructionDispatcher implements Executor {

    private final LD1Executor ld1Executor;
    private final LDAExecutor ldaExecutor;
    private final LDANExecutor ldanExecutor;
    private final LDXExecutor ldxExecutor;
    private final NOPExecutor nopExecutor;
    private final STAExecutor staExecutor;
    private final STXExecutor stxExecutor;

    public InstructionDispatcher(LD1Executor ld1Executor, LDANExecutor ldanExecutor, LDAExecutor ldaExecutor, LDXExecutor ldxExecutor, NOPExecutor nopExecutor, STAExecutor staExecutor, STXExecutor stxExecutor) {
        this.ld1Executor = ld1Executor;
        this.ldaExecutor = ldaExecutor;
        this.ldanExecutor = ldanExecutor;
        this.ldxExecutor = ldxExecutor;
        this.nopExecutor = nopExecutor;
        this.staExecutor = staExecutor;
        this.stxExecutor = stxExecutor;
    }

    @Override
    public StateModification apply(Machine machine, Instruction instruction) {
        final Executor specializedExecutor = switch (instruction.operation()) {
            case LDA -> ldaExecutor;
            case LDAN -> ldanExecutor;
            case LD1 -> ld1Executor;
            case LDX -> ldxExecutor;
            case NOP -> nopExecutor;
            case STA -> staExecutor;
            case STX -> stxExecutor;
            default -> throw new UnsupportedOperationException("Not yet implemented: " + instruction.operation());
        };

        return specializedExecutor.apply(machine, instruction);
    }
}
