package mancala;

import java.io.Serializable;

public class GameNotOverException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public GameNotOverException() {
        super("Game is not over");
    }

    public GameNotOverException(final String message) {
        super(message);
    }
}
