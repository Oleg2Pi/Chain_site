package by.polikarpov.repository;

import by.polikarpov.entity.ImagePerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagePersonRepository extends JpaRepository<ImagePerson, Integer> {
}
