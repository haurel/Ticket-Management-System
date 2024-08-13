package com.common.objects.models.api;

import com.common.types.api.ResponseStatusType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel<T> {
    private T response;
    private String message;
    private ResponseStatusType responseStatus;
}