package fr.awildelephant.mix.emulator.parser.error;

public final class UnknownTokenException extends ParseError {

    private final String token;

    public UnknownTokenException(String token) {
        this.token = token;
    }
}
