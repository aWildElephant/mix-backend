package fr.awildelephant.mmix.emulator.parser;

import fr.awildelephant.mmix.emulator.instruction.*;
import fr.awildelephant.mmix.emulator.parser.error.ParseError;
import fr.awildelephant.mmix.emulator.parser.input.InputWithLookup;
import fr.awildelephant.mmix.emulator.parser.lexer.Lexer;
import fr.awildelephant.mmix.emulator.parser.lexer.TokenType;
import fr.awildelephant.mmix.emulator.parser.lexer.token.*;
import fr.awildelephant.mmix.emulator.word.ByteHelper;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static fr.awildelephant.mmix.emulator.instruction.Operation.*;
import static fr.awildelephant.mmix.emulator.parser.lexer.TokenType.VALUE;

// TODO: explanation on what went wrong for parsing errors
@RequiredArgsConstructor
public final class Parser {

    private final AddressService addressService;

    public InstructionSequence parse(InputStream inputStream) {
        final Lexer lexer = new Lexer(new InputWithLookup(inputStream));

        return deriveInstructionSequence(lexer);
    }

    private InstructionSequence deriveInstructionSequence(Lexer lexer) {
        final List<Instruction> instructionList = new ArrayList<>();
        do {
            instructionList.add(deriveInstruction(lexer));
        } while (lexer.lookup() != EndOfFileToken.INSTANCE);

        return new InstructionSequence(instructionList);
    }

    private Instruction deriveInstruction(Lexer lexer) {
        final Token t1 = lexer.lookup();

        if (t1 instanceof OperationToken) {
            final Operation operation = transformOperation(t1.getType());

            lexer.consume();

            final Instruction.InstructionBuilder instructionBuilder = Instruction.builder();
            instructionBuilder.operation(operation);

            deriveSpecification(lexer, instructionBuilder);

            if (operation.getModification() >= 0) {
                instructionBuilder.modification(operation.getModification());
            }

            return instructionBuilder.build();
        } else {
            throw new ParseError();
        }
    }

    private Operation transformOperation(TokenType type) {
        return switch (type) {
            case NOP -> NOP;
            case ADD -> ADD;
            case FADD -> FADD;
            case SUB -> SUB;
            case FSUB -> FSUB;
            case MUL -> MUL;
            case FMUL -> FMUL;
            case DIV -> DIV;
            case FDIV -> FDIV;
            case NUM -> NUM;
            case CHAR -> CHAR;
            case HLT -> HLT;
            case SLA -> SLA;
            case SRA -> SRA;
            case SLAX -> SLAX;
            case SRAX -> SRAX;
            case SLC -> SLC;
            case SRC -> SRC;
            case MOVE -> MOVE;
            case LDA -> LDA;
            case LD1 -> LD1;
            case LD2 -> LD2;
            case LD3 -> LD3;
            case LD4 -> LD4;
            case LD5 -> LD5;
            case LD6 -> LD6;
            case LDX -> LDX;
            case LDAN -> LDAN;
            case LD1N -> LD1N;
            case LD2N -> LD2N;
            case LD3N -> LD3N;
            case LD4N -> LD4N;
            case LD5N -> LD5N;
            case LD6N -> LD6N;
            case LDXN -> LDXN;
            case STA -> STA;
            case ST1 -> ST1;
            case ST2 -> ST2;
            case ST3 -> ST3;
            case ST4 -> ST4;
            case ST5 -> ST5;
            case ST6 -> ST6;
            case STX -> STX;
            case STJ -> STJ;
            case STZ -> STZ;
            case JBUS -> JBUS;
            case IOC -> IOC;
            case IN -> IN;
            case OUT -> OUT;
            case JRED -> JRED;
            case JMP -> JMP;
            case JSJ -> JSJ;
            case JOV -> JOV;
            case JNOV -> JNOV;
            case JL -> JL;
            case JE -> JE;
            case JG -> JG;
            case JGE -> JGE;
            case JNE -> JNE;
            case JLE -> JLE;
            case JAN -> JAN;
            case JAZ -> JAZ;
            case JAP -> JAP;
            case JANN -> JANN;
            case JANZ -> JANZ;
            case JANP -> JANP;
            case J1N -> J1N;
            case J1Z -> J1Z;
            case J1P -> J1P;
            case J1NN -> J1NN;
            case J1NZ -> J1NZ;
            case J1NP -> J1NP;
            case J2N -> J2N;
            case J2Z -> J2Z;
            case J2P -> J2P;
            case J2NN -> J2NN;
            case J2NZ -> J2NZ;
            case J2NP -> J2NP;
            case J3N -> J3N;
            case J3Z -> J3Z;
            case J3P -> J3P;
            case J3NN -> J3NN;
            case J3NZ -> J3NZ;
            case J3NP -> J3NP;
            case J4N -> J4N;
            case J4Z -> J4Z;
            case J4P -> J4P;
            case J4NN -> J4NN;
            case J4NZ -> J4NZ;
            case J4NP -> J4NP;
            case J5N -> J5N;
            case J5Z -> J5Z;
            case J5P -> J5P;
            case J5NN -> J5NN;
            case J5NZ -> J5NZ;
            case J5NP -> J5NP;
            case J6N -> J6N;
            case J6Z -> J6Z;
            case J6P -> J6P;
            case J6NN -> J6NN;
            case J6NZ -> J6NZ;
            case J6NP -> J6NP;
            case JXN -> JXN;
            case JXZ -> JXZ;
            case JXP -> JXP;
            case JXNN -> JXNN;
            case JXNZ -> JXNZ;
            case JXNP -> JXNP;
            case INCA -> INCA;
            case INC1 -> INC1;
            case INC2 -> INC2;
            case INC3 -> INC3;
            case INC4 -> INC4;
            case INC5 -> INC5;
            case INC6 -> INC6;
            case INCX -> INCX;
            case DECA -> DECA;
            case DEC1 -> DEC1;
            case DEC2 -> DEC2;
            case DEC3 -> DEC3;
            case DEC4 -> DEC4;
            case DEC5 -> DEC5;
            case DEC6 -> DEC6;
            case DECX -> DECX;
            case ENTA -> ENTA;
            case ENT1 -> ENT1;
            case ENT2 -> ENT2;
            case ENT3 -> ENT3;
            case ENT4 -> ENT4;
            case ENT5 -> ENT5;
            case ENT6 -> ENT6;
            case ENTX -> ENTX;
            case ENN1 -> ENN1;
            case ENN2 -> ENN2;
            case ENN3 -> ENN3;
            case ENN4 -> ENN4;
            case ENN5 -> ENN5;
            case ENN6 -> ENN6;
            case ENNA -> ENNA;
            case ENNX -> ENNX;
            case CMPA -> CMPA;
            case FCMP -> FCMP;
            case CMP1 -> CMP1;
            case CMP2 -> CMP2;
            case CMP3 -> CMP3;
            case CMP4 -> CMP4;
            case CMP5 -> CMP5;
            case CMP6 -> CMP6;
            case CMPX -> CMPX;
            default -> null;
        };
    }

    private void deriveSpecification(Lexer lexer, Instruction.InstructionBuilder instructionBuilder) {
        final Token t0 = lexer.lookup();

        if (t0 instanceof IntegerToken addressToken) {
            instructionBuilder.address(addressService.toAddress(addressToken.getValue()));
            lexer.consume();

            if (lexer.lookup() == SpecialToken.COMMA) {
                lexer.consume();

                if (lexer.lookup().getType() == VALUE) {
                    instructionBuilder.indexSpecification((byte) consumeAndExpectInteger(lexer));
                }

                if (lexer.lookup().getType() == TokenType.LEFT_PARENTHESIS) {
                    instructionBuilder.modification(deriveModification(lexer));
                } else {
                    instructionBuilder.modification(ByteHelper.b5);
                }
            } else {
                instructionBuilder.modification(ByteHelper.b5);
            }
        } else {
            instructionBuilder.modification(ByteHelper.b5);
        }
    }

    private byte deriveModification(Lexer lexer) {
        consumeAndExpect(lexer, TokenType.LEFT_PARENTHESIS);
        final int l = consumeAndExpectInteger(lexer);
        consumeAndExpect(lexer, TokenType.COLON);
        final int r = consumeAndExpectInteger(lexer);
        consumeAndExpect(lexer, TokenType.RIGHT_PARENTHESIS);
        return (byte) (8 * l + r);
    }

    private void consumeAndExpect(Lexer lexer, TokenType expectedType) {
        if (lexer.lookup().getType() == expectedType) {
            lexer.consume();
        } else {
            throw new ParseError();
        }
    }

    private int consumeAndExpectInteger(Lexer lexer) {
        if (lexer.lookup() instanceof IntegerToken integerToken) {
            lexer.consume();
            return integerToken.getValue();
        } else {
            throw new ParseError();
        }
    }
}
