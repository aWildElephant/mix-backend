package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class InstructionHelper {

    private InstructionHelper() {

    }

    public static Instruction toInstruction(Word word) {
        return Instruction.builder()
                .operation(Operation.fromOperationCodeAndModification(word.b5(), word.b4()))
                .modification(word.b4())
                .indexSpecification(word.b3())
                .address(new Address(new TwoBytesSigned(word.sign(), word.b1(), word.b2())))
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
