package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;

public final class HLTExecutor implements OperationExecutor {

    private static final HLTExecutor INSTANCE = new HLTExecutor();

    public static HLTExecutor getInstance() {
        return INSTANCE;
    }

    private HLTExecutor() {

    }

    @Override
    public void accept(Machine machine) {
        machine.metaState().power();
    }
}
