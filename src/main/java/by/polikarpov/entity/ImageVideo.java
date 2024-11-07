package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image_video")
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
