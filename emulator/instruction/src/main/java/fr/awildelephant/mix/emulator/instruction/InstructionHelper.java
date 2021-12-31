package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.Word;

public final class InstructionHelper {

    private InstructionHelper() {

    }

    public static Instruction toInstruction(Word word) {
        return Instruction.builder()
                .operation(Operation.fromOperationCodeAndModification(word.getB5(), word.getB4()))
                .modification(word.getB4())
                .indexSpecification(word.getB3())
                .address(new Address(word.getSign(), word.getB1(), word.getB2()))
                .build();
    }

    public static Word toWord(Instruction instruction) {
        return new Word(
                instruction.getAddress().getSign(),
                instruction.getAddress().getB1(),
                instruction.getAddress().getB2(),
                instruction.getIndexSpecification(),
                instruction.getModification(),
                instruction.getOperation().getCode()
        );
    }
}
