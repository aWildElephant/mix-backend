package fr.awildelephant.mix.emulator.instruction;

public enum Operation {

    NOP(0),
    ADD(1),
    FADD(1, 6),
    SUB(2),
    FSUB(2, 6),
    MUL(3),
    FMUL(3, 6),
    DIV(4),
    FDIV(4, 6),
    NUM(5, 0),
    CHAR(5, 1),
    HLT(5, 2),
    SLA(6, 0),
    SRA(6, 1),
    SLAX(6, 2),
    SRAX(6, 3),
    SLC(6, 4),
    SRC(6, 5),
    MOVE(7),
    LDA(8),
    LD1(9),
    LD2(10),
    LD3(11),
    LD4(12),
    LD5(13),
    LD6(14),
    LDX(15),
    LDAN(16),
    LD1N(17),
    LD2N(18),
    LD3N(19),
    LD4N(20),
    LD5N(21),
    LD6N(22),
    LDXN(23),
    STA(24),
    ST1(25),
    ST2(26),
    ST3(27),
    ST4(28),
    ST5(29),
    ST6(30),
    STX(31),
    STJ(32),
    STZ(33),
    JBUS(34),
    IOC(35),
    IN(36),
    OUT(37),
    JRED(38),
    JMP(39, 0),
    JSJ(39, 1),
    JOV(39, 2),
    JNOV(39, 3),
    JL(39, 4),
    JE(39, 5),
    JG(39, 6),
    JGE(39, 7),
    JNE(39, 8),
    JLE(39, 9),
    JAN(40, 0),
    JAZ(40, 1),
    JAP(40, 2),
    JANN(40, 3),
    JANZ(40, 4),
    JANP(40, 5),
    J1N(41, 0),
    J1Z(41, 1),
    J1P(41, 2),
    J1NN(41, 3),
    J1NZ(41, 4),
    J1NP(41, 5),
    J2N(42, 0),
    J2Z(42, 1),
    J2P(42, 2),
    J2NN(42, 3),
    J2NZ(42, 4),
    J2NP(42, 5),
    J3N(43, 0),
    J3Z(43, 1),
    J3P(43, 2),
    J3NN(43, 3),
    J3NZ(43, 4),
    J3NP(43, 5),
    J4N(44, 0),
    J4Z(44, 1),
    J4P(44, 2),
    J4NN(44, 3),
    J4NZ(44, 4),
    J4NP(44, 5),
    J5N(45, 0),
    J5Z(45, 1),
    J5P(45, 2),
    J5NN(45, 3),
    J5NZ(45, 4),
    J5NP(45, 5),
    J6N(46, 0),
    J6Z(46, 1),
    J6P(46, 2),
    J6NN(46, 3),
    J6NZ(46, 4),
    J6NP(46, 5),
    JXN(47, 0),
    JXZ(47, 1),
    JXP(47, 2),
    JXNN(47, 3),
    JXNZ(47, 4),
    JXNP(47, 5),
    INCA(48, 0),
    INC1(49, 0),
    INC2(50, 0),
    INC3(51, 0),
    INC4(52, 0),
    INC5(53, 0),
    INC6(54, 0),
    INCX(55, 0),
    DECA(48, 1),
    DEC1(49, 1),
    DEC2(50, 1),
    DEC3(51, 1),
    DEC4(52, 1),
    DEC5(53, 1),
    DEC6(54, 1),
    DECX(55, 1),
    ENTA(48, 2),
    ENT1(49, 2),
    ENT2(50, 2),
    ENT3(51, 2),
    ENT4(52, 2),
    ENT5(53, 2),
    ENT6(54, 2),
    ENTX(55, 2),
    ENN1(49, 3),
    ENN2(50, 3),
    ENN3(51, 3),
    ENN4(52, 3),
    ENN5(53, 3),
    ENN6(54, 3),
    ENNA(48, 3),
    ENNX(55, 3),
    CMPA(56),
    FCMP(56, 6),
    CMP1(57),
    CMP2(58),
    CMP3(59),
    CMP4(60),
    CMP5(61),
    CMP6(62),
    CMPX(63);

    private final int code;
    private final int modification;

    Operation(int code) {
        this(code, -1);
    }

    Operation(int code, int modification) {
        this.code = code;
        this.modification = modification;
    }

    public static Operation fromOperationCodeAndModification(int operationCode, int modification) {
        for (Operation value : values()) {
            if (value.code() == operationCode) {
                final int expectedModification = value.modification();
                if (expectedModification == -1 || expectedModification == modification) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException();
    }

    public int code() {
        return code;
    }

    public int modification() {
        return modification;
    }
}
