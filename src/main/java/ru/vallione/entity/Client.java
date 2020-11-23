package ru.vallione.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_clients")
public class Client implements Serializable{

    @Schema(
            description = "Уникальный идентификатор клиента",
            example = "3",
            required = true
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;

    @Schema(
            description = "Имя клиента",
            example = "Иван"
    )
    @Column (name = "first_name")
    private String fName;

    @Schema(
            description = "Фамилия клиента",
            example = "Иванов"
    )
    @Column (name = "last_name")
    private String lName;

    @Schema(
            description = "Номер контракта",
            example = "РГС-000452321/07-2020"
    )
    @Column (name = "contract_num")
    private String contract;

    @Schema(
            description = "Адрес клиента",
            example = "г. Москва ул. Тверская д.23 стр.4 кв.22"
    )
    @Column (name = "client_location")
    private String location;

    @Schema(
            description = "Телефон клиента",
            example = "89448991212"
    )
    @Column (name = "telephone")
    private String tel;

    @Schema(
            description = "Денежные средства (RUB)",
            example = "742465.56"
    )
    @Column (name = "money_summary")
    private Double money;
}
