package com.example.springboot_microservice_task_hirzinfathoni.model.response;

public final class ResponseMessage {

    private ResponseMessage() {
        throw new UnsupportedOperationException("This is a constant class and cannot be instantiated");
    }

    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";
    public static final String FAILED = "Failed";

    public static final String DATA_CREATED = "Data successfully created.";
    public static final String DATA_UPDATED = "Data successfully modified.";
    public static final String DATA_FETCHED = "Data(s) successfully fetched.";
    public static final String DATA_DELETED = "Data successfully deleted.";
    public static final String DATA_NOT_FOUND = "Data not found.";
}
