package com.to_do_app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Task task;
}
