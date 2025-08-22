package fr.awildelephant.mix.emulator.engine.helper;

import fr.awildelephant.mix.emulator.engine.executor.InstructionDispatcher;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.instruction.InstructionSequence;
import fr.awildelephant.mix.emulator.parser.Parser;
import fr.awildelephant.mix.emulator.word.WordService;

import java.io.ByteArrayInputStream;

public final class ExecutorTestHelper {

    private ExecutorTestHelper() {

    }

    public static void execute(String instruction, Machine machine) {
        final Parser parser = new Parser();

        final InstructionSequence instructionSequence = parser.parse(new ByteArrayInputStream(instruction.getBytes()));

        final Instruction instructionToTest = instructionSequence.instructions().get(0);

        new InstructionDispatcher(new WordService()).accept(machine, instructionToTest);
    }


}
