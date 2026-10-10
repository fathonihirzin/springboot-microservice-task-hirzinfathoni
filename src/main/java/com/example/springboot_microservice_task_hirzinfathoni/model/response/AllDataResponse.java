package com.example.springboot_microservice_task_hirzinfathoni.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AllDataResponse<T> {
    String result;
    String detail;
    int code;
    List<T> data;
    int total;
}
