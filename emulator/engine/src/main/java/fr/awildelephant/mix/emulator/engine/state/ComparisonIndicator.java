package fr.awildelephant.mix.emulator.engine.state;

public class ComparisonIndicator {

    private ComparisonIndicatorState state;

    public ComparisonIndicatorState state() {
        return state;
    }

    public enum ComparisonIndicatorState { LESS, EQUAL, GREATER };
}
