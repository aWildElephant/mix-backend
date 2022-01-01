package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwoBytesSignedRegister {

    private TwoBytesSigned content = TwoBytesSigned.empty();
}
