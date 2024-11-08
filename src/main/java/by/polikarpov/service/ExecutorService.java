package by.polikarpov.service;

import by.polikarpov.entity.Executor;
import by.polikarpov.repository.ExecutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutorService {

    @Autowired
    private ExecutorRepository executorRepository;

    public Optional<Executor> findById(int id) {
        return executorRepository.findById(id);
    }
}
