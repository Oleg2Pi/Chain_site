package by.polikarpov.repository;

import by.polikarpov.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Integer> {
    public List<Work> findAllByExecutorId(Integer executorId);

//    @Query(value = "SELECT w FROM Work w WHERE w.executor.id = :executorId ORDER BY w.dateAdded DESC LIMIT 10")
//    public List<Work> findTopTenOrderWork(@Param("executorId") Integer executorId);

    public List<Work> findTop10ByExecutorIdOrderByDateAddedDesc(Integer executorId);
}
