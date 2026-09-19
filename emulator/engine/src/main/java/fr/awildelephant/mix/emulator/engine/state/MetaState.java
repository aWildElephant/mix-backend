package fr.awildelephant.mix.emulator.engine.state;

public final class MetaState {

    private boolean running;
    private int nextInstruction;

    /**
     * Starts or stops the machine.
     */
    public boolean power() {
        running = !running;
        return running;
    }

    public int instructionPointer() {
        return nextInstruction;
    }

    public void incrementInstructionPointer() {
        nextInstruction++;
    }

    public void setInstructionPointer(int value) {
        nextInstruction = value;
    }
}
