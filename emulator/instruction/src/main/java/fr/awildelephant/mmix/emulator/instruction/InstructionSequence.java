package fr.awildelephant.mmix.emulator.instruction;

import lombok.Value;

import java.util.List;

@Value
public class InstructionSequence {

    List<Instruction> instructions;
}
