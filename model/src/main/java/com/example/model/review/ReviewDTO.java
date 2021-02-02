package com.example.model.review;

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
public class ReviewDTO {
    private long id;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("rate")
    private float rate;

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("updated_at")
    private Instant updatedAt;
}
