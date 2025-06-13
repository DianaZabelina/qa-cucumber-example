package responses;

import responses.errors.ErrorBody;

import java.util.List;

public class ResponseHeader {
    private String responseDate;
    private List<ErrorBody> errors;

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public List<ErrorBody> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorBody> errors) {
        this.errors = errors;
    }
}
