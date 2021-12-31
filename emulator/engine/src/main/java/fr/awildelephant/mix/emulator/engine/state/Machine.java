package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.instruction.AddressService;

public final class Machine {

    private final SignedFiveBytesRegister registerA = new SignedFiveBytesRegister();
    private final OverflowToggle overflowToggle = new OverflowToggle();
    private final ComparisonIndicator comparisonIndicator = new ComparisonIndicator();
    private final Memory memory;

    public Machine(AddressService addressService) {
        memory = new Memory(addressService);
    }

    public SignedFiveBytesRegister getARegister() {
        return registerA;
    }

    public Memory getMemory() {
        return memory;
    }
}
