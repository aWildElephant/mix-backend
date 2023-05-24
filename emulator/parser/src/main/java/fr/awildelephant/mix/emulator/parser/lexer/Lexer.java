package fr.awildelephant.mix.emulator.parser.lexer;

import fr.awildelephant.mix.emulator.parser.error.ReadingException;
import fr.awildelephant.mix.emulator.parser.error.UnknownTokenException;
import fr.awildelephant.mix.emulator.parser.input.InputWithLookup;
import fr.awildelephant.mix.emulator.parser.lexer.token.EndOfFileToken;
import fr.awildelephant.mix.emulator.parser.lexer.token.IntegerToken;
import fr.awildelephant.mix.emulator.parser.lexer.token.OperationToken;
import fr.awildelephant.mix.emulator.parser.lexer.token.SpecialToken;
import fr.awildelephant.mix.emulator.parser.lexer.token.Token;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.awildelephant.mix.emulator.parser.lexer.TokenType.COLON;
import static fr.awildelephant.mix.emulator.parser.lexer.TokenType.COMMA;
import static fr.awildelephant.mix.emulator.parser.lexer.TokenType.LEFT_PARENTHESIS;
import static fr.awildelephant.mix.emulator.parser.lexer.TokenType.RIGHT_PARENTHESIS;
import static fr.awildelephant.mix.emulator.parser.lexer.TokenType.VALUE;
import static fr.awildelephant.mix.emulator.parser.lexer.TokenType.values;
import static java.lang.Character.isWhitespace;

public final class Lexer {

    private static final Map<String, TokenType> operatorMap = buildOperatorMap();

    public Lexer(InputWithLookup input) {
        this.input = input;
    }

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

    private Token lookup;

    public Token lookup() {
        if (lookup == null) {
            consume();
        }

        return lookup;
    }

    public void consume() {
        lookup = nextToken();
    }

    private Token nextToken() {
        try {
            final String tokenString = nextTokenString();

            if (tokenString == null) {
                return EndOfFileToken.INSTANCE;
            }

            switch (tokenString) {
                case "(" -> {
                    return SpecialToken.LEFT_PARENTHESIS;
                }
                case ")" -> {
                    return SpecialToken.RIGHT_PARENTHESIS;
                }
                case ":" -> {
                    return SpecialToken.COLON;
                }
                case "," -> {
                    return SpecialToken.COMMA;
                }
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
        } catch (IOException e) {
            throw new ReadingException(e);
        }
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
            case '(' -> {
                return "(";
            }
            case ')' -> {
                return ")";
            }
            case ':' -> {
                return ":";
            }
            case ',' -> {
                return ",";
            }
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
