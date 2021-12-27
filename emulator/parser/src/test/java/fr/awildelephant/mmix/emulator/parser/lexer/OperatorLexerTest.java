package fr.awildelephant.mmix.emulator.parser.lexer;

import fr.awildelephant.mmix.emulator.parser.lexer.token.OperationToken;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static fr.awildelephant.mmix.emulator.parser.lexer.LexerHelperTest.assertTokenization;
import static fr.awildelephant.mmix.emulator.parser.lexer.TokenType.*;

public class OperatorLexerTest {

    @ParameterizedTest
    @MethodSource("parameters")
    void it_should_tokenize_any_operator(String text, TokenType expectedToken) throws IOException {
        assertTokenization(text, List.of(new OperationToken(expectedToken, text)));
    }

    private static Stream<Arguments> parameters() {
        final EnumSet<TokenType> excluded = EnumSet.of(COLON, COMMA, LEFT_PARENTHESIS, RIGHT_PARENTHESIS, VALUE);

        return Arrays.stream(TokenType.values())
                .filter(Predicate.not(excluded::contains))
                .map(tokenType -> Arguments.of(tokenType.name(), tokenType));
    }
}
