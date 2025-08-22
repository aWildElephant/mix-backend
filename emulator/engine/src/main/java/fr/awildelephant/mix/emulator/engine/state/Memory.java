package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.word.Word;

public class Memory {

    public static final int MEMORY_SIZE = 4000;

    private final Word[] words;

    public Memory() {
        words = new Word[MEMORY_SIZE];
    }

    public Word get(Address address) {
        return get(address.toInt());
    }

    public Word get(int address) {
        checkAddress(address);

        return words[address];
    }

    public void put(Address address, Word word) {
        put(address.toInt(), word);
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
