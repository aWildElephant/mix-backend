package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.word.Word;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignedFiveBytesRegister {

    private Word word = Word.emptyWord();
}
