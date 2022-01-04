package fr.awildelephant.mix.emulator.word;

import lombok.RequiredArgsConstructor;

// FIXME: converting TwoBytesSigned to int and back like a naughty boi
@RequiredArgsConstructor
public class TwoBytesSignedMathService {

    private final TwoBytesSignedService naughtyBoiService;

    public TwoBytesSigned add(TwoBytesSigned a, TwoBytesSigned b) {
        return naughtyBoiService.fromInt(naughtyBoiService.toInt(a) + naughtyBoiService.toInt(b));
    }
}
