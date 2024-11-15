package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "work")
@ToString(exclude = {"executor"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "executor_id", nullable = false)
    private Executor executor;

    private String name;

    @Column(name = "date_added")
    private Timestamp dateAdded;

    private String description;

    private String file;

    private String type;

}
