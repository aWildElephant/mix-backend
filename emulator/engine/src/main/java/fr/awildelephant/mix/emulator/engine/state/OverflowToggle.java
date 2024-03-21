package fr.awildelephant.mix.emulator.engine.state;

public final class OverflowToggle {

    private boolean state;

    public boolean state() {
        return state;
    }

    public void on() {
        state = true;
    }
}
