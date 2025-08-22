package fr.awildelephant.mix.emulator.engine.state;

public final class OverflowToggle {

    private boolean state;

    public boolean state() {
        return state;
    }

    public void set() {
        state = true;
    }

    public void clear() {
        state = false;
    }
}
