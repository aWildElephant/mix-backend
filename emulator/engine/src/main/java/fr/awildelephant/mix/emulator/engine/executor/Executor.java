package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Instruction;

import java.util.function.BiFunction;

public interface Executor extends BiFunction<Machine, Instruction, StateModification> {
}
