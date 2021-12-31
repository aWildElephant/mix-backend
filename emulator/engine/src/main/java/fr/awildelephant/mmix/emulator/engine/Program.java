package fr.awildelephant.mmix.emulator.engine;

import fr.awildelephant.mmix.emulator.engine.modification.StateModification;
import fr.awildelephant.mmix.emulator.engine.state.Machine;
import fr.awildelephant.mmix.emulator.instruction.Address;
import fr.awildelephant.mmix.emulator.instruction.Instruction;
import fr.awildelephant.mmix.emulator.instruction.InstructionHelper;
import fr.awildelephant.mmix.emulator.word.Word;
import lombok.Value;

import java.util.Set;

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
