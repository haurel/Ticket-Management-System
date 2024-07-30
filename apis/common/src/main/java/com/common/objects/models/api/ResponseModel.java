package com.common.objects.models.api;

import com.common.types.api.ResponseStatusType;

public record ResponseModel<T>(T Response, String Message, ResponseStatusType ResponseStatus) {}