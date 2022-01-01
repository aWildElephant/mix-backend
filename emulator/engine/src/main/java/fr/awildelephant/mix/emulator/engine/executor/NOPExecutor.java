package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.modification.NOP;
import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Instruction;

public final class NOPExecutor implements Executor {

    @Override
    public StateModification apply(Machine machine, Instruction instruction) {
        return NOP.instance();
    }
}
