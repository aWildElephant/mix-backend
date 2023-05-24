package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.instruction.AddressService;
import lombok.Getter;

@Getter
public final class Machine {

    private final SignedFiveBytesRegister registerA = new SignedFiveBytesRegister();
    private final SignedFiveBytesRegister registerX = new SignedFiveBytesRegister();
    private final SignedTwoBytesRegister registerI1 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI2 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI3 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI4 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI5 = new SignedTwoBytesRegister();
    private final SignedTwoBytesRegister registerI6 = new SignedTwoBytesRegister();
    private final OverflowToggle overflowToggle = new OverflowToggle();
    private final ComparisonIndicator comparisonIndicator = new ComparisonIndicator();
    private final Memory memory;

    public Machine(AddressService addressService) {
        memory = new Memory(addressService);
    }
}
