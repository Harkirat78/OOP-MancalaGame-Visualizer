package mancala;

import java.io.Serializable;

public class InvalidMoveException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public InvalidMoveException() {
        super("Invalid move");
    }

    public InvalidMoveException(final String message) {
        super(message);
    }
}
