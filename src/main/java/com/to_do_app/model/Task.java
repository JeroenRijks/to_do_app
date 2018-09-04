package com.to_do_app.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="tasks")
public class Task {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;
    // FK -> PK

    @Column(name = "importance", nullable = false)
    private PriorityTypes importance;

    @Column(name = "date")
    private Date deadline;      // dd/mm/yy format
    // Either Low, Medium or High


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public PriorityTypes getImportance() { return importance; }
    public void setImportance(PriorityTypes importance) { this.importance = importance; }

    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
