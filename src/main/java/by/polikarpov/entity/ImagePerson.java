package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image_of_person")
@ToString(exclude = {"person"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;

    private byte[] file;
}
