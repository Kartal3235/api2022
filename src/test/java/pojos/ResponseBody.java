package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBody {

    private String status;
    private Data data;
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseBody() {
    }

    /**
     *
     * @param data
     * @param message
     * @param status
     */
    public ResponseBody(String status, Data data, String message) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
