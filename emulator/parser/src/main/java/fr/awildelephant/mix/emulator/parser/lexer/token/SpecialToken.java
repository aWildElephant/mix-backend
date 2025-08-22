package fr.awildelephant.mix.emulator.parser.lexer.token;

import fr.awildelephant.mix.emulator.parser.lexer.TokenType;

public enum SpecialToken implements Token {
    COLON(TokenType.COLON, ":"),
    COMMA(TokenType.COMMA, ","),
    LEFT_PARENTHESIS(TokenType.LEFT_PARENTHESIS, "("),
    MINUS(TokenType.MINUS, "-"),
    RIGHT_PARENTHESIS(TokenType.RIGHT_PARENTHESIS, ")");

    private final TokenType type;
    private final String text;

    SpecialToken(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    public TokenType type() {
        return type;
    }

    public String text() {
        return text;
    }
}
