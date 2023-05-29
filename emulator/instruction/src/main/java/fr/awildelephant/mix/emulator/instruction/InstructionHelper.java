package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.Word;

public final class InstructionHelper {

    private InstructionHelper() {

    }

    public static Instruction toInstruction(Word word) {
        // TODO
//        final Operation operation = Operation.fromOperationCodeAndModification(word.b5(), word.b4());
//        final Modification modification = new Modification(word.b4());
//        final byte indexSpecification = word.b3();
//        final Address address = new Address(new TwoBytesSigned(word.sign(), word.b1(), word.b2()));
//
//        return new Instruction(operation, modification, indexSpecification, address);
        throw new UnsupportedOperationException();
    }

    public static Word toWord(Instruction instruction) {
        // TODO
//        final TwoBytesSigned addressValue = instruction.address().value();
//
//        return new Word(addressValue.sign(), addressValue.b1(), addressValue.b2(), instruction.indexSpecification(), instruction.modification().value(), instruction.operation().code());
        throw new UnsupportedOperationException();
    }
}
