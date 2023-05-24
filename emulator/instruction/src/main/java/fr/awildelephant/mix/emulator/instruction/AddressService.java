package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.TwoBytesSignedService;

public final class AddressService {

    private final TwoBytesSignedService twoBytesSignedService;

    public AddressService(TwoBytesSignedService twoBytesSignedService) {
        this.twoBytesSignedService = twoBytesSignedService;
    }

    public Address toAddress(int value) {
        return new Address(twoBytesSignedService.fromInt(value));
    }

    public int toInteger(Address address) {
        return twoBytesSignedService.toInt(address.value());
    }
}
