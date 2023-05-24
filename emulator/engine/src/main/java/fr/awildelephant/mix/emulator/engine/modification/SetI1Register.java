package fr.awildelephant.mix.emulator.engine.modification;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import lombok.Value;

@Value
public class SetI1Register implements StateModification {

    TwoBytesSigned newValue;

    @Override
    public void accept(Machine machine) {
        machine.getRegisterI1().setContent(newValue);
    }
}
