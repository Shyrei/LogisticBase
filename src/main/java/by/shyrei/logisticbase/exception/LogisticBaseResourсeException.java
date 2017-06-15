package by.shyrei.logisticbase.exception;

/**
 * Project LogisticBase
 * Created on 15.06.2017.
 * author Shyrei Uladzimir
 */
public class LogisticBaseResourсeException extends Exception {
    public LogisticBaseResourсeException() {
    }

    public LogisticBaseResourсeException(String message) {
        super(message);
    }

    public LogisticBaseResourсeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogisticBaseResourсeException(Throwable cause) {
        super(cause);
    }

    public LogisticBaseResourсeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
