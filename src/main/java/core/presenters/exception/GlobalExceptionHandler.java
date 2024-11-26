package core.presenters.exception;

import core.exception.BaseException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        if (e instanceof BaseException) {
            return Response.status(((BaseException) e).getHttpCode()).entity(
                    new Object() {
                        public final String code = ((BaseException) e).getCode();
                        public final String message = e.getMessage();
                        public final Integer httpCode = ((BaseException) e).getHttpCode();
                    }).build();
        }

        return Response.status(500).entity(
                new Object() {
                    public final String code = "INTERNAL_SERVER_ERROR";
                    public final String message = "Internal Server Error";
                    public final Integer httpCode = 500;
                }).build();
    }
}
