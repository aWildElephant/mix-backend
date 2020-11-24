package fr.awildelephant.mmix.emulator.parser.lexer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static fr.awildelephant.mmix.emulator.parser.lexer.LexerHelperTest.assertThrown;
import static fr.awildelephant.mmix.emulator.parser.lexer.LexerHelperTest.assertTokenization;
import static fr.awildelephant.mmix.emulator.parser.lexer.TokenType.*;

class LexerTest {

    @Test
    void it_should_tokenize_a_single_operator() throws IOException {
        assertTokenization("ADD", List.of(new Token(TokenType.ADD, "ADD")));
    }

    @Test
    void it_should_conserve_case_for_operator_text() throws IOException {
        assertTokenization("aDd", List.of(new Token(ADD, "aDd")));
    }

    @Test
    void it_should_ignore_spaces_between_tokens() throws IOException {
        assertTokenization("ADD   CHAR", List.of(
                new Token(ADD, "ADD"),
                new Token(CHAR, "CHAR")
        ));
    }

    @Test
    void it_should_tokenize_special_characters() throws IOException {
        assertTokenization(", ( : )", List.of(
                new Token(COMMA, ","),
                new Token(LEFT_PARENTHESIS, "("),
                new Token(COLON, ":"),
                new Token(RIGHT_PARENTHESIS, ")")
        ));
    }

    @Test
    void it_should_throw_an_exception_on_unknown_operator() {
        assertThrown("ADORABLE").isInstanceOf(UnknownTokenException.class);
    }

    @Test
    void it_should_tokenize_valid_tokens_in_the_wrong_order() throws IOException {
        assertTokenization("420 ADD(NOP,:)", List.of(
                new Token(VALUE, "420"),
                new Token(ADD, "ADD"),
                new Token(LEFT_PARENTHESIS, "("),
                new Token(NOP, "NOP"),
                new Token(COMMA, ","),
                new Token(COLON, ":"),
                new Token(RIGHT_PARENTHESIS, ")")
        ));
    }
}