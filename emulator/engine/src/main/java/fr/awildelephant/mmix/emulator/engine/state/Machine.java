package fr.awildelephant.mmix.emulator.engine.state;

public class Machine {

    private final SignedFiveBytesRegister registerA = new SignedFiveBytesRegister();

    private final OverflowToggle overflowToggle = new OverflowToggle();
    private final ComparisonIndicator comparisonIndicator = new ComparisonIndicator();

    private final Memory memory = new Memory();

    public SignedFiveBytesRegister getARegister() {
        return registerA;
    }

    public Memory getMemory() {
        return memory;
    }
}
