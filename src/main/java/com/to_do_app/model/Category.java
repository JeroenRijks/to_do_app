package com.to_do_app.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Column(name = "category_name", nullable = false)
    private String name;

    @Id
    @NotNull
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;


    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Task> task;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}


