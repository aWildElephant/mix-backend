package fr.awildelephant.mmix.emulator.instruction;

import lombok.Getter;

import static fr.awildelephant.mmix.emulator.word.ByteHelper.*;

@Getter
public enum Operation {

    NOP(b0),
    ADD(b1),
    FADD(b1, b6),
    SUB(b2),
    FSUB(b2, b6),
    MUL(b3),
    FMUL(b3, b6),
    DIV(b4),
    FDIV(b4, b6),
    NUM(b5, b0),
    CHAR(b5, b1),
    HLT(b5, b2),
    SLA(b6, b0),
    SRA(b6, b1),
    SLAX(b6, b2),
    SRAX(b6, b3),
    SLC(b6, b4),
    SRC(b6, b5),
    MOVE(b7),
    LDA(b8),
    LD1(b9),
    LD2(b10),
    LD3(b11),
    LD4(b12),
    LD5(b13),
    LD6(b14),
    LDX(b15),
    LDAN(b16),
    LD1N(b17),
    LD2N(b18),
    LD3N(b19),
    LD4N(b20),
    LD5N(b21),
    LD6N(b22),
    LDXN(b23),
    STA(b24),
    ST1(b25),
    ST2(b26),
    ST3(b27),
    ST4(b28),
    ST5(b29),
    ST6(b30),
    STX(b31),
    STJ(b32),
    STZ(b33),
    JBUS(b34),
    IOC(b35),
    IN(b36),
    OUT(b37),
    JRED(b38),
    JMP(b39, b0),
    JSJ(b39, b1),
    JOV(b39, b2),
    JNOV(b39, b3),
    JL(b39, b4),
    JE(b39, b5),
    JG(b39, b6),
    JGE(b39, b7),
    JNE(b39, b8),
    JLE(b39, b9),
    JAN(b40, b0),
    JAZ(b40, b1),
    JAP(b40, b2),
    JANN(b40, b3),
    JANZ(b40, b4),
    JANP(b40, b5),
    J1N(b41, b0),
    J1Z(b41, b1),
    J1P(b41, b2),
    J1NN(b41, b3),
    J1NZ(b41, b4),
    J1NP(b41, b5),
    J2N(b42, b0),
    J2Z(b42, b1),
    J2P(b42, b2),
    J2NN(b42, b3),
    J2NZ(b42, b4),
    J2NP(b42, b5),
    J3N(b43, b0),
    J3Z(b43, b1),
    J3P(b43, b2),
    J3NN(b43, b3),
    J3NZ(b43, b4),
    J3NP(b43, b5),
    J4N(b44, b0),
    J4Z(b44, b1),
    J4P(b44, b2),
    J4NN(b44, b3),
    J4NZ(b44, b4),
    J4NP(b44, b5),
    J5N(b45, b0),
    J5Z(b45, b1),
    J5P(b45, b2),
    J5NN(b45, b3),
    J5NZ(b45, b4),
    J5NP(b45, b5),
    J6N(b46, b0),
    J6Z(b46, b1),
    J6P(b46, b2),
    J6NN(b46, b3),
    J6NZ(b46, b4),
    J6NP(b46, b5),
    JXN(b47, b0),
    JXZ(b47, b1),
    JXP(b47, b2),
    JXNN(b47, b3),
    JXNZ(b47, b4),
    JXNP(b47, b5),
    INCA(b48, b0),
    INC1(b49, b0),
    INC2(b50, b0),
    INC3(b51, b0),
    INC4(b52, b0),
    INC5(b53, b0),
    INC6(b54, b0),
    INCX(b55, b0),
    DECA(b48, b1),
    DEC1(b49, b1),
    DEC2(b50, b1),
    DEC3(b51, b1),
    DEC4(b52, b1),
    DEC5(b53, b1),
    DEC6(b54, b1),
    DECX(b55, b1),
    ENTA(b48, b2),
    ENT1(b49, b2),
    ENT2(b50, b2),
    ENT3(b51, b2),
    ENT4(b52, b2),
    ENT5(b53, b2),
    ENT6(b54, b2),
    ENTX(b55, b2),
    ENN1(b49, b3),
    ENN2(b50, b3),
    ENN3(b51, b3),
    ENN4(b52, b3),
    ENN5(b53, b3),
    ENN6(b54, b3),
    ENNA(b48, b3),
    ENNX(b55, b3),
    CMPA(b56),
    FCMP(b56, b6),
    CMP1(b57),
    CMP2(b58),
    CMP3(b59),
    CMP4(b60),
    CMP5(b61),
    CMP6(b62),
    CMPX(b63);

    private final byte code;
    private final byte modification;

    Operation(byte code) {
        this(code, (byte) -1);
    }

    Operation(byte code, byte modification) {
        this.code = code;
        this.modification = modification;
    }

    public static Operation fromOperationCodeAndModification(byte operationCode, byte modification) {
        for (Operation value : values()) {
            if (value.getCode() == operationCode) {
                final byte expectedModification = value.getModification();
                if (expectedModification == -1 || expectedModification == modification) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException();
    }
}
