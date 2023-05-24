package fr.awildelephant.mix.emulator.instruction;

public record Modification(byte value) {

    public FieldSpecification toFieldSpecification() {
        return new FieldSpecification(value / 8, value % 8);
    }
}
