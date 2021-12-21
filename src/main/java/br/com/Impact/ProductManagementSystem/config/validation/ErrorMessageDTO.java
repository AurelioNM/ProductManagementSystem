package br.com.Impact.ProductManagementSystem.config.validation;

public class ErrorMessageDTO {

    private String field;
    private String error;

    public ErrorMessageDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
