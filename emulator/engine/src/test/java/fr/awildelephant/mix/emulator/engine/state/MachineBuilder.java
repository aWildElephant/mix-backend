package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.instruction.AddressService;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class MachineBuilder {

    // TODO: replace AddressService by methods in WordHelper
    private final Machine machine = new Machine(new AddressService());

    private MachineBuilder() {

    }

    public static MachineBuilder builder() {
        return new MachineBuilder();
    }

    public MachineBuilder withARegister(Word content) {
        machine.registerA().content(content);
        return this;
    }

    public MachineBuilder withXRegister(final Word content) {
        machine.registerX().content(content);
        return this;
    }

    public MachineBuilder withI1Register(final TwoBytesSigned content) {
        machine.registerI1().content(content);
        return this;
    }

    public MachineBuilder withMemoryCell(int address, Word content) {
        machine.memory().put(address, content);
        return this;
    }

    public Machine build() {
        return machine;
    }
}
