package fr.awildelephant.mmix.emulator.parser.lexer;

import fr.awildelephant.mmix.emulator.parser.input.InputWithLookup;
import fr.awildelephant.mmix.emulator.parser.lexer.token.EndOfFileToken;
import fr.awildelephant.mmix.emulator.parser.lexer.token.Token;
import org.assertj.core.api.AbstractThrowableAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public final class LexerHelperTest {

    private LexerHelperTest() {

    }
    static AbstractThrowableAssert<?, ? extends Throwable> assertThrown(String input) {
        return assertThatThrownBy(() -> tokenize(input));
    }

    static void assertTokenization(String input, List<Token> expectedTokens) {
        final List<Token> actualTokens = tokenize(input);

        assertThat(actualTokens).isEqualTo(expectedTokens);
    }

    private static List<Token> tokenize(String input) {
        final Lexer lexer = new Lexer(new InputWithLookup(inputStream(input)));

        final List<Token> actualTokens = new ArrayList<>();

        Token token;
        while ((token = lexer.lookup()) != EndOfFileToken.INSTANCE) {
            actualTokens.add(token);
            lexer.consume();
        }
        return actualTokens;
    }

    private static ByteArrayInputStream inputStream(String input) {
        return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    }
}
