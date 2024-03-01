package myproject.base.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class BaseException extends Exception {
    protected int statusCode;
    protected ExceptionBody body;

    protected BaseException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.body = new ExceptionBody(message);
    }
}
