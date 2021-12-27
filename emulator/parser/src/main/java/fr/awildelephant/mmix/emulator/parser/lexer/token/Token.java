package fr.awildelephant.mmix.emulator.parser.lexer.token;

import fr.awildelephant.mmix.emulator.parser.lexer.TokenType;

public interface Token {

    TokenType getType();

    String getText();
}
