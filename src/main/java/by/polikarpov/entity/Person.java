package by.polikarpov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "person")
@Data
@ToString(exclude = {"image", "executor"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username_tg")
    private String usernameTG;

    private String phone;

    @Column(name = "chat_id")
    private long chatId;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ImagePerson image;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Executor executor;
}
