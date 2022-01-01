package fr.awildelephant.mix.emulator.engine;

import fr.awildelephant.mix.emulator.engine.executor.InstructionDispatcher;
import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.instruction.InstructionHelper;
import fr.awildelephant.mix.emulator.word.Word;
import lombok.Value;

// TODO: bootstraping the program by writing instructions to memory
@Value
public class Program {

    InstructionDispatcher instructionDispatcher;

    public void run(Machine machine, Address programStart) {
        final Instruction instruction = loadInstruction(machine, programStart);

        final StateModification modification = instructionDispatcher.apply(machine, instruction);

        modification.accept(machine);

        // TODO: get to next instruction
    }

    private Instruction loadInstruction(Machine machine, Address address) {
        final Word instructionWordFormat = machine.getMemory().get(address);

        return InstructionHelper.toInstruction(instructionWordFormat);
    }
}
