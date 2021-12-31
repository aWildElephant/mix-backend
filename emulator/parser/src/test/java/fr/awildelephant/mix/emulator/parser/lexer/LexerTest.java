package fr.awildelephant.mix.emulator.parser.lexer;

import fr.awildelephant.mix.emulator.parser.error.UnknownTokenException;
import fr.awildelephant.mix.emulator.parser.lexer.token.IntegerToken;
import fr.awildelephant.mix.emulator.parser.lexer.token.OperationToken;
import fr.awildelephant.mix.emulator.parser.lexer.token.SpecialToken;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.awildelephant.mix.emulator.parser.lexer.LexerHelperTest.assertThrown;
import static fr.awildelephant.mix.emulator.parser.lexer.LexerHelperTest.assertTokenization;

class LexerTest {

    @Test
    void it_should_tokenize_a_single_operator() {
        assertTokenization("ADD", List.of(new OperationToken(TokenType.ADD, "ADD")));
    }

    @Test
    void it_should_conserve_case_for_operator_text() {
        assertTokenization("aDd", List.of(new OperationToken(TokenType.ADD, "aDd")));
    }

    @Test
    void it_should_ignore_spaces_between_tokens() {
        assertTokenization("ADD   CHAR", List.of(
                new OperationToken(TokenType.ADD, "ADD"),
                new OperationToken(TokenType.CHAR, "CHAR")
        ));
    }

    @Test
    void it_should_tokenize_special_characters() {
        assertTokenization(", ( : )", List.of(
                SpecialToken.COMMA,
                SpecialToken.LEFT_PARENTHESIS,
                SpecialToken.COLON,
                SpecialToken.RIGHT_PARENTHESIS
        ));
    }

    @Test
    void it_should_throw_an_exception_on_unknown_operator() {
        assertThrown("ADORABLE").isInstanceOf(UnknownTokenException.class);
    }

    @Test
    void it_should_tokenize_valid_tokens_in_the_wrong_order() {
        assertTokenization("420 ADD(NOP,:)", List.of(
                new IntegerToken(420),
                new OperationToken(TokenType.ADD, "ADD"),
                SpecialToken.LEFT_PARENTHESIS,
                new OperationToken(TokenType.NOP, "NOP"),
                SpecialToken.COMMA,
                SpecialToken.COLON,
                SpecialToken.RIGHT_PARENTHESIS
        ));
    }
}