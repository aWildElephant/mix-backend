package fr.awildelephant.mix.emulator.parser.lexer.token;

import fr.awildelephant.mix.emulator.parser.lexer.TokenType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpecialToken implements Token {
    COLON(TokenType.COLON, ":"),
    COMMA(TokenType.COMMA, ","),
    LEFT_PARENTHESIS(TokenType.LEFT_PARENTHESIS, "("),
    RIGHT_PARENTHESIS(TokenType.RIGHT_PARENTHESIS, ")");

    private final TokenType type;
    private final String text;
}
