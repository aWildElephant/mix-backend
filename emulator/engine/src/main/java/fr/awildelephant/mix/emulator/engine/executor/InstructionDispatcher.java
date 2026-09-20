package fr.awildelephant.mix.emulator.engine.executor;

import fr.awildelephant.mix.emulator.engine.state.Machine;
import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.FieldSpecification;
import fr.awildelephant.mix.emulator.instruction.Instruction;
import fr.awildelephant.mix.emulator.instruction.Operation;
import fr.awildelephant.mix.emulator.word.WordService;

import java.util.function.BiConsumer;

public final class InstructionDispatcher implements BiConsumer<Machine, Instruction> {

    private final WordService wordService;

    public InstructionDispatcher(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public void accept(Machine machine, Instruction instruction) {
        final Operation operation = instruction.operation();

        if (operation == Operation.NOP) {
            return;
        }

        final Address address = instruction.address();
        final byte indexSpecification = instruction.indexSpecification();
        final FieldSpecification fieldSpecification = instruction.modification().toFieldSpecification();

        final OperationExecutor specializedExecutor = switch (operation) {
            case HLT -> HLTExecutor.getInstance();
            // Loading operators
            case LDA -> new LDAExecutor(address, indexSpecification, fieldSpecification);
            case LDX -> new LDXExecutor(fieldSpecification, address, indexSpecification);
            case LD1 -> new LD1Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD2 -> new LD2Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD3 -> new LD3Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD4 -> new LD4Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD5 -> new LD5Executor(wordService, fieldSpecification, address, indexSpecification);
            case LD6 -> new LD6Executor(wordService, fieldSpecification, address, indexSpecification);
            case LDAN -> new LDANExecutor(fieldSpecification, address, indexSpecification);
            case LDXN -> new LDXNExecutor(fieldSpecification, address, indexSpecification);
            case LD1N -> new LD1NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD2N -> new LD2NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD3N -> new LD3NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD4N -> new LD4NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD5N -> new LD5NExecutor(wordService, fieldSpecification, address, indexSpecification);
            case LD6N -> new LD6NExecutor(wordService, fieldSpecification, address, indexSpecification);
            // Storing operators
            case STA -> new STAExecutor(fieldSpecification, address, indexSpecification);
            case STX -> new STXExecutor(fieldSpecification, address, indexSpecification);
            case ST1 -> new ST1Executor(fieldSpecification, address, indexSpecification);
            case ST2 -> new ST2Executor(fieldSpecification, address, indexSpecification);
            case ST3 -> new ST3Executor(fieldSpecification, address, indexSpecification);
            case ST4 -> new ST4Executor(fieldSpecification, address, indexSpecification);
            case ST5 -> new ST5Executor(fieldSpecification, address, indexSpecification);
            case ST6 -> new ST6Executor(fieldSpecification, address, indexSpecification);
            case STJ -> new STJExecutor(fieldSpecification, address, indexSpecification);
            case STZ -> new STZExecutor(fieldSpecification, address, indexSpecification);
            // Arithmetic operators
            case ADD -> new ADDExecutor(fieldSpecification, address, indexSpecification);
            case SUB -> new SUBExecutor(fieldSpecification, address, indexSpecification);
            case MUL -> new MULExecutor(fieldSpecification, address, indexSpecification);
            case DIV -> new DIVExecutor(fieldSpecification, address, indexSpecification);
            // Address transfer operators
            case ENTA -> new ENTAExecutor(address.value(), indexSpecification);
            case INCA -> new INCAExecutor(address.value());
            // TODO: lots of stuff here
            case INCX -> new INCXExecutor(address.value());
            // TODO: several INC* and the DEC*
            // Comparison operators
            case CMPA -> new CMPAExecutor(fieldSpecification, address);
            case CMPX -> new CMPXExecutor(fieldSpecification, address);
            case CMP1 -> new CMP1Executor(fieldSpecification, address);
            case CMP2 -> new CMP2Executor(fieldSpecification, address);
            case CMP3 -> new CMP3Executor(fieldSpecification, address);
            case CMP4 -> new CMP4Executor(fieldSpecification, address);
            case CMP5 -> new CMP5Executor(fieldSpecification, address);
            case CMP6 -> new CMP6Executor(fieldSpecification, address);
            // Jump operators
            case JMP -> new JMPExecutor(address.value());
            case JSJ -> new JSJExecutor(address.value());
            case JOV -> new JOVExecutor(address.value());
            case JNOV -> new JNOVExecutor(address.value());
            case JL -> new JLExecutor(address.value());
            case JE -> new JEExecutor(address.value());
            case JG -> new JGExecutor(address.value());
            case JGE -> new JGEExecutor(address.value());
            case JNE -> new JNEExecutor(address.value());
            case JLE -> new JLEExecutor(address.value());
            case JAN -> new JANExecutor(address.value());
            case JAZ -> new JAZExecutor(address.value());
            case JAP -> new JAPExecutor(address.value());
            case JANN -> new JANNExecutor(address.value());
            case JANZ -> new JANZExecutor(address.value());
            case JANP -> new JANPExecutor(address.value());
            case JXN -> new JXNExecutor(address.value());
            case JXZ -> new JXZExecutor(address.value());
            case JXP -> new JXPExecutor(address.value());
            case JXNN -> new JXNNExecutor(address.value());
            case JXNZ -> new JXNZExecutor(address.value());
            case JXNP -> new JXNPExecutor(address.value());
            // TODO: Ji*
            // TODO: SLA, SRA, SLAX, SRAX, SLC, SRC
            // TODO: MOVE
            // TODO: input-output operators
            // TODO: conversion operators
            default -> throw new UnsupportedOperationException("Not yet implemented: " + operation);
        };

        specializedExecutor.accept(machine);
    }
}
