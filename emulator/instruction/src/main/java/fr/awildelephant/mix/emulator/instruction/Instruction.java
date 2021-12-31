package fr.awildelephant.mix.emulator.instruction;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Instruction {

    Operation operation;
    byte modification;
    byte indexSpecification;
    Address address;
}
