package fr.awildelephant.mix.emulator.parser.error;

import lombok.Value;

@Value
public class UnknownTokenException extends ParseError {

    String token;
}
