package fr.awildelephant.mix.emulator.instruction;

import fr.awildelephant.mix.emulator.word.TwoBytesSignedService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AddressService {

    private final TwoBytesSignedService twoBytesSignedService;

    public Address toAddress(int value) {
        return new Address(twoBytesSignedService.fromInt(value));
    }

    public int toInteger(Address address) {
        return twoBytesSignedService.toInt(address.value());
    }
}
