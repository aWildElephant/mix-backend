package fr.awildelephant.mix.emulator.word;

public record ComputationResult<T extends AbstractBytesHolder>(T result, boolean overflow) {
}
