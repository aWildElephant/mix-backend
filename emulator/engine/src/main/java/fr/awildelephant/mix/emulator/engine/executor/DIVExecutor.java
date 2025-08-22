package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.word.DivisionResult;
import fr.awildelephant.mix.emulator.word.MathUtils;
import fr.awildelephant.mix.emulator.word.TenBytesSigned;
import fr.awildelephant.mix.emulator.word.Word;

public class DIVExecutor extends AbstractOperationExecutor {

    private final FieldSpecification fieldSpecification;
    private final Address address;
    private final byte indexSpecification;

    public DIVExecutor(FieldSpecification fieldSpecification, Address address, byte indexSpecification) {
        this.fieldSpecification = fieldSpecification;
        this.address = address;
        this.indexSpecification = indexSpecification;
    }

    @Override
    public void accept(Machine machine) {
        final Word denominator = fieldSpecification.load(machine.memory().get(indexingProcess(machine, address, indexSpecification)));
        final Word rA = machine.registerA().content();
        final Word rX = machine.registerX().content();

        final TenBytesSigned numerator = TenBytesSigned.from(rA.sign(), rA.b1(), rA.b2(), rA.b3(), rA.b4(), rA.b5(), rX.b1(), rX.b2(), rX.b3(), rX.b4(), rX.b5());

        final DivisionResult result = MathUtils.divide(numerator, denominator);

        machine.registerA().content(result.result());
        final Word remainder = result.remainder();
        remainder.sign(rA.sign());
        machine.registerX().content(remainder);
        if (result.overflow()) {
            machine.overflowToggle().set();
        }
    }
}
