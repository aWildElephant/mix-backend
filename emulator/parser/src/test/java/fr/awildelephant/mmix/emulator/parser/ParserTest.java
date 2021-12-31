package fr.awildelephant.mmix.emulator.parser;

import fr.awildelephant.mmix.emulator.instruction.AddressService;
import fr.awildelephant.mmix.emulator.instruction.Instruction;
import fr.awildelephant.mmix.emulator.instruction.Operation;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ParserTest {

    private final AddressService addressService = new AddressService();

    @Test
    void it_should_parse_all_examples_from_page_128() {
        assertParsing(
                """
                LDA 2000,2(0:3)
                LDA 2000,2(1:3)
                LDA 2000,(1:3)
                LDA 2000
                LDA -2000,4
                """,
                List.of(
                        numericRepresentation(2000, 2, 3, 8),
                        numericRepresentation(2000, 2, 11, 8),
                        numericRepresentation(2000, 0, 11, 8),
                        numericRepresentation(2000, 0, 5, 8),
                        numericRepresentation(-2000, 4, 5, 8)
                )
        );
    }

    private void assertParsing(String source, List<Instruction> expected) {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8));
        final List<Instruction> actual = new Parser(addressService).parse(inputStream).getInstructions();
        assertThat(actual).isEqualTo(expected);
    }

    private Instruction numericRepresentation(int address, int indexSpecifiation, int modification, int operationCode) {
        return Instruction.builder()
                .operation(getOperation(operationCode))
                .modification((byte) modification)
                .indexSpecification((byte) indexSpecifiation)
                .address(addressService.toAddress(address))
                .build();
    }

    private Operation getOperation(int operationCode) {
        return Stream.of(Operation.values())
                .filter(operation -> operation.getCode() == operationCode)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}