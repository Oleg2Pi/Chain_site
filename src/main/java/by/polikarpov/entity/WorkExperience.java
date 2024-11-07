package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "work_experience")
@ToString(exclude = {"resumes"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String category;

    @OneToMany(mappedBy = "workExperience", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resume> resumes;
}
