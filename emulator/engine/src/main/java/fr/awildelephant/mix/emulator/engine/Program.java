package fr.awildelephant.mix.emulator.engine;

import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.instruction.InstructionHelper;
import fr.awildelephant.mix.emulator.word.Word;
import lombok.Value;

import java.util.Set;

// TODO: bootstraping the program by writing instructions to memory
@Value
public class Program {

    Executor executor;

    public void run(Machine machine, Address programStart) {
        final Instruction instruction = loadInstruction(machine, programStart);

        final Set<StateModification> modificationsToApply = executor.apply(machine, instruction);

        modificationsToApply.forEach(modification -> modification.accept(machine));

        // TODO: get to next instruction
    }

    private Instruction loadInstruction(Machine machine, Address address) {
        final Word instructionWordFormat = machine.getMemory().get(address);

        return InstructionHelper.toInstruction(instructionWordFormat);
    }
}
