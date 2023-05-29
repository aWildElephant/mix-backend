package fr.awildelephant.mix.emulator.engine;

import fr.awildelephant.mix.emulator.engine.executor.InstructionDispatcher;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.instruction.InstructionHelper;
import fr.awildelephant.mix.emulator.word.Word;

// TODO: bootstraping the program by writing instructions to memory
public final class Program {

    private final InstructionDispatcher instructionDispatcher;

    public Program(InstructionDispatcher instructionDispatcher) {
        this.instructionDispatcher = instructionDispatcher;
    }

    public void run(Machine machine, Address programStart) {
        final Instruction instruction = loadInstruction(machine, programStart);

        instructionDispatcher.accept(machine, instruction);

        // TODO: get to next instruction
    }

    private Instruction loadInstruction(Machine machine, Address address) {
        final Word instructionWordFormat = machine.memory().get(address);

        return InstructionHelper.toInstruction(instructionWordFormat);
    }
}
