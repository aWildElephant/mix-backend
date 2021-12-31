package fr.awildelephant.mix.emulator.parser.lexer.token;

import fr.awildelephant.mix.emulator.parser.lexer.TokenType;
import lombok.Value;

@Value
public class IntegerToken implements Token {

    int value;

    @Override
    public TokenType getType() {
        return TokenType.VALUE;
    }

    @Override
    public String getText() {
        return Integer.toString(value);
    }
}
