package fr.awildelephant.mix.emulator.word;

public class DivisionResult extends ComputationResult<Word> {

    private final Word remainder;

    public DivisionResult(final Word quotient, final Word remainder, final boolean overflow) {
        super(quotient, overflow);
        this.remainder = remainder;
    }

    public Word remainder() {
        return remainder;
    }
}
