package fr.awildelephant.mix.emulator.parser.lexer.token;

import fr.awildelephant.mix.emulator.parser.lexer.TokenType;

public record IntegerToken(int value) implements Token {

    @Override
    public TokenType type() {
        return TokenType.VALUE;
    }

    @Override
    public String text() {
        return Integer.toString(value);
    }
}
