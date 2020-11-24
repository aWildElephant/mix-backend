package fr.awildelephant.mmix.emulator.engine.command;

import fr.awildelephant.mmix.emulator.engine.state.Word;

public class FieldSpecification {

    private final int start;
    private final int end;

    public FieldSpecification(int start, int end) {
        checkValidStart(start);
        checkValidEnd(start, end);

        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    private void checkValidStart(int start) {
        if (start < 0 || start > 5) {
            throw new IllegalArgumentException("start of field specification out of bounds");
        }
    }

    private void checkValidEnd(int start, int end) {
        if (start < 0 || start > 5) {
            throw new IllegalArgumentException("end of field specification out of bounds");
        }

        if (start > end) {
            throw new IllegalArgumentException("end of field specification before start");
        }
    }

    /**
     * Apply the field specification on the given word.
     */
    public Word select(Word word) {
        final Word modifiedWord = new Word();
        return null;
    }
}
