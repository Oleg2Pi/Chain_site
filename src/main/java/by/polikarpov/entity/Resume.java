package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume")
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

    @OneToOne
    @JoinColumn(name = "activity_area_id")
    private ActivityArea activityArea;

    @OneToOne
    @JoinColumn(name = "work_experience_id")
    private WorkExperience workExperience;

    @OneToOne
    @JoinColumn(name = "user_status_id")
    private UserStatus userStatus;

    @Column(name = "information_yourself")
    private String informationYourself;
}
