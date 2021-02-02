package com.example.reviewsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "reviews")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "comment", length = 250)
    private String comment;

    @Column(name = "rate")
    private float rate;

    @Column(name = "uid")
    private String uid;

    @Column(name = "product_id")
    private long productId;
}
