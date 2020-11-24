package fr.awildelephant.mmix.emulator.engine.command;

import fr.awildelephant.mmix.emulator.engine.state.MIXState;

public class NOP implements Command {

    private static final NOP INSTANCE = new NOP();

    public static NOP nop() {
        return INSTANCE;
    }

    @Override
    public void execute(MIXState state) {
        // NOP
    }
}
