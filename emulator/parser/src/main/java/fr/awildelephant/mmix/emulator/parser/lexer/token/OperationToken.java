package fr.awildelephant.mmix.emulator.parser.lexer.token;

import fr.awildelephant.mmix.emulator.parser.lexer.TokenType;
import lombok.Value;

@Value
public class OperationToken implements Token {

    TokenType type;
    String text;
}
