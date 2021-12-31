package fr.awildelephant.mix.emulator.parser.lexer;

import fr.awildelephant.mix.emulator.parser.lexer.token.OperationToken;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class OperatorLexerTest {

    @ParameterizedTest
    @MethodSource("parameters")
    void it_should_tokenize_any_operator(String text, TokenType expectedToken) {
        LexerHelperTest.assertTokenization(text, List.of(new OperationToken(expectedToken, text)));
    }

    private static Stream<Arguments> parameters() {
        final EnumSet<TokenType> excluded = EnumSet.of(TokenType.COLON, TokenType.COMMA, TokenType.LEFT_PARENTHESIS, TokenType.RIGHT_PARENTHESIS, TokenType.VALUE);

        return Arrays.stream(TokenType.values())
                .filter(Predicate.not(excluded::contains))
                .map(tokenType -> Arguments.of(tokenType.name(), tokenType));
    }
}
