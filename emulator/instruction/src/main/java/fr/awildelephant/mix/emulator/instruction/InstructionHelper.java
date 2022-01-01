package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class InstructionHelper {

    private InstructionHelper() {

    }

    public static Instruction toInstruction(Word word) {
        return Instruction.builder()
                .operation(Operation.fromOperationCodeAndModification(word.getB5(), word.getB4()))
                .modification(word.getB4())
                .indexSpecification(word.getB3())
                .address(new Address(new TwoBytesSigned(word.getSign(), word.getB1(), word.getB2())))
                .build();
    }

    public static Word toWord(Instruction instruction) {
        final TwoBytesSigned addressValue = instruction.getAddress().value();

        return new Word(
                addressValue.sign(),
                addressValue.b1(),
                addressValue.b2(),
                instruction.getIndexSpecification(),
                instruction.getModification(),
                instruction.getOperation().getCode()
        );
    }
}
