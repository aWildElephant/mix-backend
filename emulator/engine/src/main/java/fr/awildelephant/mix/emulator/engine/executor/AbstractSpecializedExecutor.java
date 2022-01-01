package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.instruction.FieldSpecificationService;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.word.Word;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractSpecializedExecutor implements Executor {

    private final FieldSpecificationService fieldSpecificationService;

    protected Word applyFieldSpecification(Word value, Instruction instruction) {
        return fieldSpecificationService.applySpecification(instruction.getModification(), value);
    }

    protected Word applyFieldSpecification(Word newValue, Word originalValue, Instruction instruction) {
        return fieldSpecificationService.applySpecification(instruction.getModification(), newValue, originalValue);
    }
}
