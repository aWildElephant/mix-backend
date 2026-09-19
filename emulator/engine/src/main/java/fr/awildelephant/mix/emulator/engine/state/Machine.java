package fr.awildelephant.mix.emulator.engine.state;

public final class Machine {

    private final SignedFiveBytesRegister registerA = new SignedFiveBytesRegister();
    private final SignedFiveBytesRegister registerX = new SignedFiveBytesRegister();
    private final SignedTwoBytesRegister registerI1 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI2 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI3 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI4 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI5 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI6 = new SignedTwoBytesRegister();
    /**
     * Register J holds two bytes, it behaves as if its sign is always +.
     */
    private final SignedTwoBytesRegister registerJ = new SignedTwoBytesRegister();
    private final OverflowToggle overflowToggle = new OverflowToggle();
    private final ComparisonIndicator comparisonIndicator = new ComparisonIndicator();
    private final Memory memory = new Memory();

    /**
     * Additional data on the state of the machine.
     */
    private final MetaState metaState = new MetaState();

    public SignedFiveBytesRegister registerA() {
        return registerA;
    }

    public SignedFiveBytesRegister registerX() {
        return registerX;
    }

    public SignedTwoBytesRegister registerI1() {
        return registerI1;
    }

    public SignedTwoBytesRegister registerI2() {
        return registerI2;
    }

    public SignedTwoBytesRegister registerI3() {
        return registerI3;
    }

    public SignedTwoBytesRegister registerI4() {
        return registerI4;
    }

    public SignedTwoBytesRegister registerI5() {
        return registerI5;
    }

    public SignedTwoBytesRegister registerI6() {
        return registerI6;
    }

    public SignedTwoBytesRegister registerJ() {
        return registerJ;
    }

    public OverflowToggle overflowToggle() {
        return overflowToggle;
    }

    public ComparisonIndicator comparisonIndicator() {
        return comparisonIndicator;
    }

    public Memory memory() {
        return memory;
    }

    public MetaState metaState() {
        return metaState;
    }
}
