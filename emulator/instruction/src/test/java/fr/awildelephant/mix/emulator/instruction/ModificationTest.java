package fr.awildelephant.mix.emulator.instruction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ModificationTest {

    @Test
    void fieldSpecification_should_create_a_field_specification() {
        final Modification modification = new Modification((byte) 19);
        assertThat(modification.toFieldSpecification()).isEqualTo(new FieldSpecification(2, 3));
    }
}