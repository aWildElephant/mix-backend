package fr.awildelephant.mix.emulator.parser.lexer.token;

import fr.awildelephant.mix.emulator.parser.lexer.TokenType;
import lombok.Value;

@Value
public class OperationToken implements Token {

    TokenType type;
    String text;
}
