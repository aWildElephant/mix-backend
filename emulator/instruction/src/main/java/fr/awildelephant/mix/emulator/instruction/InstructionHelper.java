package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class InstructionHelper {

    private InstructionHelper() {

    }

    public static Instruction toInstruction(Word word) {
        final Operation operation = Operation.fromOperationCodeAndModification(word.b5(), word.b4());
        final Modification modification = new Modification(word.b4());
        final byte indexSpecification = (byte) word.b3();

        final TwoBytesSigned addressValue = TwoBytesSigned.from(word.sign(), word.b1(), word.b2());
        final Address address = new Address(addressValue);

        return new Instruction(operation, modification, indexSpecification, address);
    }

    public static Word toWord(Instruction instruction) {
        final TwoBytesSigned addressValue = instruction.address().value();

        return Word.from(addressValue.sign(), addressValue.b1(), addressValue.b2(), instruction.indexSpecification(), instruction.modification().value(), instruction.operation().code());
    }
}
