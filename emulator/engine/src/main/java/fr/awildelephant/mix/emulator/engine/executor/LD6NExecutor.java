package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.WordService;

public final class LD6NExecutor extends LDiExecutor {

    public LD6NExecutor(WordService wordService, FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        super(wordService, fieldSpecification, address, indexSpecification, Machine::registerI6);
    }
}