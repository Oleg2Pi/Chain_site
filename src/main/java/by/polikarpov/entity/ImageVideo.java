package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image_video")
@ToString(exclude = {"work"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "work_id", nullable = false, unique = true)
    private Work work;

    private byte[] file;

    private String type;
}
