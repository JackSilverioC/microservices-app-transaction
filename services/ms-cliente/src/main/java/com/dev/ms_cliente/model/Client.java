package com.dev.ms_cliente.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("client")
public class Client {

    @Id
    private String id;
    private String name;
    private Integer age;
}
