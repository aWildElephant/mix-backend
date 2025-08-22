package fr.awildelephant.mix.emulator.instruction;

public record Modification(int value) {

    public FieldSpecification toFieldSpecification() {
        return new FieldSpecification(value >> 3, value & 7);
    }
}
