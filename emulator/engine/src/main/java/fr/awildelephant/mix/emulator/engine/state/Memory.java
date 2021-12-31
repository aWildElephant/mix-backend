package fr.awildelephant.mix.emulator.engine.state;

import fr.awildelephant.mix.emulator.instruction.Address;
import fr.awildelephant.mix.emulator.instruction.AddressService;
import fr.awildelephant.mix.emulator.word.Word;

public class Memory {

    public static final int MEMORY_SIZE = 4000;

    private final AddressService addressService;

    private final Word[] words;

    public Memory(AddressService addressService) {
        this.addressService = addressService;
        words = new Word[MEMORY_SIZE];
    }

    public Word get(Address address) {
        return get(addressService.toInteger(address));
    }

    public Word get(int address) {
        checkAddress(address);

        return words[address];
    }

    public void put(Address address, Word word) {
        put(addressService.toInteger(address), word);
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
