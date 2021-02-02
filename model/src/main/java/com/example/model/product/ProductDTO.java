package com.example.model.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private long price;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category_id")
    private long categoryId;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("updated_at")
    private Instant updatedAt;
}
