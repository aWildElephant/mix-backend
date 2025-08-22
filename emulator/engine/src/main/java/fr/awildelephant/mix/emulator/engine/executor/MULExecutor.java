package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.ComputationResult;
import fr.awildelephant.mix.emulator.word.MathUtils;
import fr.awildelephant.mix.emulator.word.TenBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public final class MULExecutor extends AbstractOperationExecutor {

    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public MULExecutor(FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        final Word memoryValue = machine.memory().get(indexingProcess(machine, address, indexSpecification));

        final Word newValue = fieldSpecification.load(memoryValue);

        final Word registerAContent = machine.registerA().content();

        final ComputationResult<TenBytesSigned> computation = MathUtils.multiply(registerAContent, newValue);

        final TenBytesSigned result = computation.result();
        final Word registerAValue = Word.from(result.sign(),
                result.getByte(0),
                result.getByte(1),
                result.getByte(2),
                result.getByte(3),
                result.getByte(4));
        final Word registerXValue = Word.from(result.sign(),
                result.getByte(5),
                result.getByte(6),
                result.getByte(7),
                result.getByte(8),
                result.getByte(9));

        machine.registerA().content(registerAValue);
        machine.registerX().content(registerXValue);
        if (computation.overflow()) {
            machine.overflowToggle().set();
        }
    }
}
