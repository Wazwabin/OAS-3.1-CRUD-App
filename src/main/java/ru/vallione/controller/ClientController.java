package ru.vallione.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vallione.entity.Client;
import ru.vallione.model.ErrorObject;
import ru.vallione.repository.ClientRepository;

import java.util.Optional;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
@Tag(name = "client", description = "API client ")
public class ClientController {

    private ClientRepository repository;

    @Operation(
            summary = "Поиск всех клиентов",
            description = "Поиск всех клиентов",
            tags = "client"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "УСПЕШНО!",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Client.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ОШИБКА: неверный запрос",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "ОШИБКА: необходимо аутентифицироваться",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Operation(
            summary = "Поиск клиента по ID",
            description = "Поиск клиента по ID",
            tags = "client"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "УСПЕШНО!",
                            content = @Content(
                                    schema = @Schema(implementation = Client.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "ОШИБКА: клиент не найден",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ОШИБКА: неверный запрос",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<?> getById(@PathVariable(value="id")Long id){
        try {
            Optional<Client> optional = repository.findById(id);
            if(optional.isPresent()){
                return ResponseEntity.ok(optional.get());
            } else {
                return notFound();
            }
        } catch (Exception e){
            return badRequest(e);
        }
    }

    @Operation(
            summary = "Создание нового клиента",
            description = "Создание нового клиента",
            tags = "client"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "УСПЕШНО!",
                            content = @Content(
                                    schema = @Schema(implementation = Client.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ОШИБКА: неверный запрос",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "ОШИБКА: такой клиент уже существует",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody Client client){
        try {
            Optional<Client> optional = repository.findById(client.getClient_id());
            if(optional.isPresent()){
                return conflict();
            } else {
                return ResponseEntity.ok(repository.save(client));
            }
        } catch (Exception e) {
            return badRequest(e);
        }
    }

    @Operation(
            summary = "Обновление клиента",
            description = "Обновление клиента",
            tags = "client"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "УСПЕШНО!",
                            content = @Content(
                                    schema = @Schema(implementation = Client.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "ОШИБКА: клиент не найден",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ОШИБКА: неверный запрос",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> update(@RequestBody Client client){
        try{
            Optional<Client> optional = repository.findById(client.getClient_id());
            if(optional.isPresent()){
                return ResponseEntity.ok(repository.save(client));
            } else {
                return notFound();
            }
        } catch (Exception e) {
            return badRequest(e);
        }
    }

    @Operation(
            summary = "Удаление клиента",
            description = "Удаление клиента",
            tags = "client"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "УСПЕШНО!",
                            content = @Content(
                                    schema = @Schema(implementation = Client.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "ОШИБКА: клиент не найден",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "ОШИБКА: неверный запрос",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        try{
            Optional<Client> optional = repository.findById(id);
            if(optional.isPresent()){
                Client client = optional.get();
                repository.delete(client);
                return ResponseEntity.ok(client);
            } else {
                return notFound();
            }
        } catch (Exception e) {
            return badRequest(e);
        }
    }

    private ResponseEntity<?> notFound(){
        return new ResponseEntity<>(
                new ErrorObject(HttpStatus.NOT_FOUND.toString(), "ОШИБКА: Клиент не найден"),
                HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<?> badRequest(Throwable throwable){
        return new ResponseEntity<>(
                new ErrorObject(HttpStatus.BAD_REQUEST.toString(), "ОШИБКА: Неверный запрос"),
                HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> conflict(){
        return new ResponseEntity<>(
                new ErrorObject(HttpStatus.CONFLICT.toString(), "ОШИБКА: Такой клиент уже существует"),
                HttpStatus.CONFLICT);
    }

}
