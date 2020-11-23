package ru.vallione.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {

    @Schema(
            description = "HTTP ОШИБКА",
            example = "400"
    )
    private String errorCode;

    @Schema(
            description = "ОШИБКА описания",
            example = "Такой клиент отсутствует в базе данных"
    )
    private String errorDescription;

}
