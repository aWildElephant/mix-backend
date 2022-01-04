package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.modification.SetI1Register;
import fr.awildelephant.mix.emulator.engine.modification.StateModification;
import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.word.TwoBytesSigned;
import fr.awildelephant.mix.emulator.word.TwoBytesSignedMathService;
import fr.awildelephant.mix.emulator.word.Word;
import fr.awildelephant.mix.emulator.word.WordService;

public final class LD1Executor extends AbstractSpecializedExecutor {

    private final WordService wordService;

    public LD1Executor(TwoBytesSignedMathService twoBytesSignedMathService, FieldSpecificationService fieldSpecificationService, WordService wordService) {
        super(twoBytesSignedMathService, fieldSpecificationService);

        this.wordService = wordService;
    }

    @Override
    public StateModification apply(Machine machine, Instruction instruction) {
        final Address address = indexingProcess(machine, instruction);
        final Word memoryValue = machine.getMemory().get(address);

        final TwoBytesSigned newValue = wordService.extract(applyFieldSpecification(memoryValue, instruction));

        // TODO: indexing process

        return new SetI1Register(newValue);
    }
}
