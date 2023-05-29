package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Instruction;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface OperationExecutor extends Consumer<Machine> {
}
