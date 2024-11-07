package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "executor")
@ToString(exclude = {"person", "resume", "works"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Executor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;

    @OneToOne(mappedBy = "executor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Resume resume;

    @OneToMany(mappedBy = "executor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Work> works;
}
