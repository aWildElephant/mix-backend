package fr.awildelephant.mmix.emulator.engine.command;

import fr.awildelephant.mmix.emulator.engine.state.MIXState;

public interface Command {

    void execute(MIXState state);
}
