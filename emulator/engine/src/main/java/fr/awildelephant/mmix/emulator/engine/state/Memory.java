package fr.awildelephant.mmix.emulator.engine.state;

public class Memory {

    private final Word[] words;

    public Memory(int memorySizeInWords) {
        words = new Word[memorySizeInWords];
    }

    public Word get(int address) {
        checkAddress(address);

        return words[address];
    }

    public void put(int address, Word word) {
        checkAddress(address);

        words[address] = word;
    }

    private void checkAddress(int address) {
        if (address < 0 || address > words.length) {
            throw new IndexOutOfBoundsException("Cannot access memory address " + address);
        }
    }
}
