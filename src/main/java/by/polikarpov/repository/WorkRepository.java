package by.polikarpov.repository;

import by.polikarpov.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Integer> {
    public List<Work> findAllByExecutorId(Integer executorId);
}
