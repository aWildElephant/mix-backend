package fr.awildelephant.mix.emulator.parser.lexer.token;

import fr.awildelephant.mix.emulator.parser.lexer.TokenType;

public record OperationToken(TokenType type, String text) implements Token {

}
