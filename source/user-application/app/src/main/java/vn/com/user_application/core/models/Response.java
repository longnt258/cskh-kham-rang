package vn.com.user_application.core.models;

public class Response<T> {
    private T data;
    private String message;
    private int status = 1;

    public Response() {}

    public Response(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public int getStatus(){
        return this.status;
    }

    public T getData() {
        return data;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
