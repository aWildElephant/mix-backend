package fr.awildelephant.mix.emulator.instruction;

import lombok.Value;

import java.util.List;

@Value
public class InstructionSequence {

    List<Instruction> instructions;
}
