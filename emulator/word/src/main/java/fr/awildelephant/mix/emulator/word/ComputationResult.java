package fr.awildelephant.mix.emulator.word;

public class ComputationResult<T extends AbstractBytesHolder> {

    private final T result;
    private final boolean overflow;

    public ComputationResult(T result, boolean overflow) {
        this.result = result;
        this.overflow = overflow;
    }

    public T result() {
        return result;
    }

    public boolean overflow() {
        return overflow;
    }
}
