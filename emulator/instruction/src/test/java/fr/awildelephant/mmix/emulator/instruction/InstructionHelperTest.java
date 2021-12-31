package fr.awildelephant.mmix.emulator.instruction;

import fr.awildelephant.mmix.emulator.word.ByteHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InstructionHelperTest {

    private final AddressService addressService = new AddressService();

    @Test
    void it_should_transform_an_instruction_to_its_word_form_and_back() {
        final Instruction instruction = new Instruction(Operation.LDA, ByteHelper.b5, ByteHelper.b0, addressService.toAddress(2000));

        assertThat(InstructionHelper.toInstruction(InstructionHelper.toWord(instruction))).isEqualTo(instruction);
    }
}