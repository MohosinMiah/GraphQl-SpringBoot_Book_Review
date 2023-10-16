package com.springgraphql.springgraphql.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class InputCreateBook {
    @Column(nullable = false)
    private String title;
    private String author;
}

