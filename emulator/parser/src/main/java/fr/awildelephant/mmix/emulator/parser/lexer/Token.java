package fr.awildelephant.mmix.emulator.parser.lexer;

import lombok.Value;

@Value // TODO: créer des sous-classes pour les différents types de tokens
public class Token {

    TokenType type;
    String text;
}
