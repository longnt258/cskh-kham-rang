package vn.com.user_application.core.models;

public class Response<T> {
    private T data;
    private String message;

    public Response(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }


    public String getMessage() {
        return message;
    }

}
