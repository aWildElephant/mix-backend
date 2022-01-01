package fr.awildelephant.mix.emulator.instruction;

import lombok.Value;

// TODO: use TwoBytesSigned
@Value
public class Address {

    byte sign;
    byte b1;
    byte b2;
}
