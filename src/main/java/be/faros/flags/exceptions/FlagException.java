package be.faros.flags.exceptions;

public class FlagException extends RuntimeException {
    public FlagException(String flag) {
        super("Flag %s toggled off".formatted(flag));
    }
}
