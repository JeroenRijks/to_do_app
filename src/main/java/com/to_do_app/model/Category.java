package com.to_do_app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Column(name = "category_name", nullable = false)
    private String name;

    @Id
    @NotNull
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;


    @JsonIgnore // Prevents  recursion between categories and tasks
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // cascade = CascadeType.ALL
    private List<Task> task;

    public Int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTask() {
        return task;
    }
    public void setTask(List<Task> task) {
        this.task = task;
    }
}


