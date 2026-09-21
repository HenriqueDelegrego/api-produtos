package com.delegrego.api_produtos.exception;

import java.time.Instant;
import java.util.List;

public record FieldErrorResponse(
		int status,
        String message,
        Instant timestamp,
        List<FieldError> fieldErrors
) {}