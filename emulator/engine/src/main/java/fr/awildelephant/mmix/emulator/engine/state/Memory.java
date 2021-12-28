package fr.awildelephant.mmix.emulator.engine.state;

import fr.awildelephant.mmix.emulator.instruction.Address;
import fr.awildelephant.mmix.emulator.instruction.AddressHelper;
import fr.awildelephant.mmix.emulator.word.Word;

public class Memory {

    public static final int MEMORY_SIZE = 4000;
    private final Word[] words;

    public Memory() {
        words = new Word[MEMORY_SIZE];
    }

    public Word get(Address address) {
        return get(AddressHelper.toInteger(address));
    }

    public Word get(int address) {
        checkAddress(address);

        return words[address];
    }

    public void put(Address address, Word word) {
        put(AddressHelper.toInteger(address), word);
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
