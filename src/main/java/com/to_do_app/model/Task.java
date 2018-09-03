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
    private String task;

    @Column(name = "category", nullable = false)
    private String category;
    // FK -> PK

    @Column(name = "importance", nullable = false)
    private String importance;
    @Column(name = "date")
    private Date deadline;      // dd/mm/yy format
    // Either Low, Medium or High








//    @ManyToMany
//    @JoinTable(name = "resource_language", joinColumns = { @JoinColumn(name = "resource_id") },
//            inverseJoinColumns = { @JoinColumn(name = "language_id") })
//    @Column(name = "language", nullable = true)
//    @JsonIgnoreProperties({"projects", "resources"})
//    private Set<Language> languages = new HashSet<>();

}
