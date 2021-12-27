package fr.awildelephant.mmix.emulator.parser.lexer;

import fr.awildelephant.mmix.emulator.parser.input.InputWithLookup;
import fr.awildelephant.mmix.emulator.parser.lexer.token.IntegerToken;
import fr.awildelephant.mmix.emulator.parser.lexer.token.OperationToken;
import fr.awildelephant.mmix.emulator.parser.lexer.token.SpecialToken;
import fr.awildelephant.mmix.emulator.parser.lexer.token.Token;
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
                return SpecialToken.LEFT_PARENTHESIS;
            case ")":
                return SpecialToken.RIGHT_PARENTHESIS;
            case ":":
                return SpecialToken.COLON;
            case ",":
                return SpecialToken.COMMA;
        }

        final TokenType operator = operatorMap.get(tokenString.toUpperCase());
        if (operator != null) {
            return new OperationToken(operator, tokenString);
        }

        try {
            return new IntegerToken(Integer.parseInt(tokenString));
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
