package com.live.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) UNSIGNED", nullable = false)
    private Long id;

    @Column(length = 255, nullable = false)
    private String queue;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String payload;

    @Column(columnDefinition = "TINYINT(3) UNSIGNED", nullable = false)
    private int attempts;

    @Column(columnDefinition = "INT(10) UNSIGNED", nullable = true)
    private Integer reserved_at;

    @Column(columnDefinition = "INT(10) UNSIGNED", nullable = false)
    private Integer available_at;

    @Column(columnDefinition = "INT(10) UNSIGNED", nullable = false)
    private Integer created_at;

}
