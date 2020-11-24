package fr.awildelephant.mmix.emulator.engine.command;

import fr.awildelephant.mmix.emulator.engine.state.MIXState;
import fr.awildelephant.mmix.emulator.engine.state.Word;

public class LDA implements Command {

    private final int memoryAddress;
    private final FieldSpecification fieldSpecification;

    public LDA(int memoryAddress, FieldSpecification fieldSpecification) {
        this.memoryAddress = memoryAddress;
        this.fieldSpecification = fieldSpecification;
    }

    @Override
    public void execute(MIXState state) {
        final Word memoryContents = state.getMemory().get(memoryAddress);

        final Word modifiedWord = fieldSpecification.select(memoryContents);

        state.getARegister().setWord(modifiedWord);
    }
}
