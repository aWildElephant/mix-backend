package fr.awildelephant.mmix.emulator.parser.lexer.token;

import fr.awildelephant.mmix.emulator.parser.lexer.TokenType;

import static fr.awildelephant.mmix.emulator.parser.lexer.TokenType.END_OF_FILE;

public final class EndOfFileToken implements Token {

    public static final EndOfFileToken INSTANCE = new EndOfFileToken();

    private EndOfFileToken() {

    }

    @Override
    public TokenType getType() {
        return END_OF_FILE;
    }

    @Override
    public String getText() {
        return "\nEOF";
    }
}
