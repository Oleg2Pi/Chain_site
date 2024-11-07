package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resume")
@ToString(exclude = {"executor", "activityArea", "workExperience", "userStatus"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "executor_id", nullable = false, unique = true)
    private Executor executor;

    @ManyToOne
    @JoinColumn(name = "activity_area_id")
    private ActivityArea activityArea;

    @ManyToOne
    @JoinColumn(name = "work_experience_id")
    private WorkExperience workExperience;

    @ManyToOne
    @JoinColumn(name = "user_status_id")
    private UserStatus userStatus;

    @Column(name = "information_yourself")
    private String informationYourself;
}
