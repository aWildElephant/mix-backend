package fr.awildelephant.mmix.emulator.engine.state;

public class MIXState {

    public static final int MEMORY_SIZE_IN_WORDS = 4000;

    private final SignedFiveBytesRegister registerA = new SignedFiveBytesRegister();

    private final OverflowToggle overflowToggle = new OverflowToggle();
    private final ComparisonIndicator comparisonIndicator = new ComparisonIndicator();

    private final Memory memory = new Memory(MEMORY_SIZE_IN_WORDS);

    public SignedFiveBytesRegister getARegister() {
        return registerA;
    }

    public Memory getMemory() {
        return memory;
    }
}
