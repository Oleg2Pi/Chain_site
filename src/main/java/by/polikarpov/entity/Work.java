package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Base64;

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

    private byte[] file;

    private String type;

    public String getBase64Data() {
        return Base64.getEncoder().encodeToString(file);
    }
}
