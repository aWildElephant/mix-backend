package fr.awildelephant.mmix.emulator.parser.lexer;

import lombok.Value;

@Value
public class UnknownTokenException extends RuntimeException {

    String token;
}
