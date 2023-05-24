package fr.awildelephant.mix.emulator.instruction;

public record Instruction(Operation operation, Modification modification, byte indexSpecification, Address address) {

    public static InstructionBuilder builder() {
        return new InstructionBuilder();
    }

    public static class InstructionBuilder {
        private Operation operation;
        private Modification modification;
        private byte indexSpecification;
        private Address address;
        
        public InstructionBuilder operation(Operation operation) {
            this.operation = operation;
            return this;
        }

        public InstructionBuilder modification(byte modification) {
            this.modification = new Modification(modification);
            return this;
        }

        public InstructionBuilder indexSpecification(byte indexSpecifiation) {
            this.indexSpecification = indexSpecifiation;
            return this;
        }

        public InstructionBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public Instruction build() {
            return new Instruction(operation, modification, indexSpecification, address);
        }
    }
}
