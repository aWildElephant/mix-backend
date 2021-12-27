package fr.awildelephant.mmix.emulator.parser.lexer.token;

import fr.awildelephant.mmix.emulator.parser.lexer.TokenType;
import lombok.Value;

import static fr.awildelephant.mmix.emulator.parser.lexer.TokenType.VALUE;

@Value
public class IntegerToken implements Token {

    int value;

    @Override
    public TokenType getType() {
        return VALUE;
    }

    @Override
    public String getText() {
        return Integer.toString(value);
    }
}
