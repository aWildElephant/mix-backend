package fr.awildelephant.mix.emulator.word;

// FIXME: converting TwoBytesSigned to int and back like a naughty boi
public final class TwoBytesSignedMathService {

    private final TwoBytesSignedService naughtyBoiService;

    public TwoBytesSignedMathService(TwoBytesSignedService naughtyBoiService) {
        this.naughtyBoiService = naughtyBoiService;
    }

    public TwoBytesSigned add(TwoBytesSigned a, TwoBytesSigned b) {
        return naughtyBoiService.fromInt(naughtyBoiService.toInt(a) + naughtyBoiService.toInt(b));
    }
}
