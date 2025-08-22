package fr.awildelephant.mix.emulator.word;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof final ComputationResult<?> other)) {
            return false;
        }

        return overflow == other.overflow
                && Objects.equals(result, other.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, overflow);
    }
}
