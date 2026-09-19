package fr.awildelephant.mix.emulator.engine.state;

public class ComparisonIndicator {

    private State state;

    public State state() {
        return state;
    }

    public void state(State result) {
        state = result;
    }

    public enum State {LESS, EQUAL, GREATER}

    ;
}
