package fr.awildelephant.mix.emulator.engine.modification;

import fr.awildelephant.mix.emulator.engine.state.Machine;

public final class NOP implements StateModification {

    private static final NOP INSTANCE = new NOP();

    public static StateModification instance() {
        return INSTANCE;
    }

    private NOP() {

    }

    @Override
    public void accept(Machine machine) {
        // NOP
    }
}
