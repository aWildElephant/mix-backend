package fr.awildelephant.mix.emulator.instruction;

public record Modification(int value) {

    public FieldSpecification toFieldSpecification() {
        // TODO: use bitwise operators
        return new FieldSpecification(value / 8, value % 8);
    }
}
