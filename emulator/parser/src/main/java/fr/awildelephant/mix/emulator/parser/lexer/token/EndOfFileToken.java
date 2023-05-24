package fr.awildelephant.mix.emulator.parser.lexer.token;

import fr.awildelephant.mix.emulator.parser.lexer.TokenType;

public final class EndOfFileToken implements Token {

    public static final EndOfFileToken INSTANCE = new EndOfFileToken();

    private EndOfFileToken() {

    }

    @Override
    public TokenType type() {
        return TokenType.END_OF_FILE;
    }

    @Override
    public String text() {
        return "\nEOF";
    }
}
