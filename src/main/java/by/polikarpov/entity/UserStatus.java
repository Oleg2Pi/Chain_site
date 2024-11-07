package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user_status")
@ToString(exclude = {"resumes"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String category;

    @OneToMany(mappedBy = "userStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resume> resumes;
}
