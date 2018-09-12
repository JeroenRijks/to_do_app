package com.to_do_app.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="task")
public class Task {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "task_name", nullable = false)
    private String name;

    // JJJ TODO: Not showing up in json files
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "importance", nullable = false)
    private PriorityTypes importance;

    @Column(name = "deadline")
    private Date deadline;
    // Either Low, Medium or High

    // Getters & Setters

    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

//    public List<>

    public PriorityTypes getImportance() { return importance; }
    public void setImportance(PriorityTypes importance) { this.importance = importance; }

    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
