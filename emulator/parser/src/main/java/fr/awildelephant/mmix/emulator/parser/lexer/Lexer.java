package fr.awildelephant.mmix.emulator.parser.lexer;

import fr.awildelephant.mmix.emulator.parser.input.InputWithLookup;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.awildelephant.mmix.emulator.parser.lexer.TokenType.*;
import static java.lang.Character.isWhitespace;

@RequiredArgsConstructor
public final class Lexer {

    private static final Map<String, TokenType> operatorMap = buildOperatorMap();

    private static Map<String, TokenType> buildOperatorMap() {
        final EnumSet<TokenType> excluded = EnumSet.of(
                LEFT_PARENTHESIS,
                RIGHT_PARENTHESIS,
                COLON,
                COMMA,
                VALUE
        );

        return Stream.of(values())
                .filter(Predicate.not(excluded::contains))
                .collect(Collectors.toMap(TokenType::name, Function.identity()));

    }

    private final InputWithLookup input;

    public Token nextToken() throws IOException {
        final String tokenString = nextTokenString();

        if (tokenString == null) {
            return null;
        }

        switch (tokenString) {
            case "(":
                return new Token(LEFT_PARENTHESIS, tokenString);
            case ")":
                return new Token(RIGHT_PARENTHESIS, tokenString);
            case ":":
                return new Token(COLON, tokenString);
            case ",":
                return new Token(COMMA, tokenString);
        }

        final TokenType operator = operatorMap.get(tokenString.toUpperCase());
        if (operator != null) {
            return new Token(operator, tokenString);
        }

        try {
            Integer.parseInt(tokenString);

            return new Token(VALUE, tokenString);
        } catch (NumberFormatException e) {
            // NOP
        }

        throw new UnknownTokenException(tokenString);
    }

    private String nextTokenString() throws IOException {
        while (isWhitespace(input.lookup())) {
            input.consume();
        }

        final int firstCharacter = input.lookup();
        if (firstCharacter == -1) {
            return null;
        }
        input.consume();

        switch (firstCharacter) {
            case '(':
                return "(";
            case ')':
                return ")";
            case ':':
                return ":";
            case ',':
                return ",";
        }

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.appendCodePoint(firstCharacter);

        int character;
        while (!isSpecial(character = input.lookup())) {
            stringBuilder.appendCodePoint(character);
            input.consume();
        }

        return stringBuilder.toString();
    }

    private boolean isSpecial(int codepoint) {
        return Character.isWhitespace(codepoint)
                || codepoint == '('
                || codepoint == ')'
                || codepoint == ':'
                || codepoint == ','
                || codepoint == -1;
    }
}
