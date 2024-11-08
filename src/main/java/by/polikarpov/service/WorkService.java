package by.polikarpov.service;

import by.polikarpov.entity.Work;
import by.polikarpov.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    public List<Work> findByAllById(Integer id) {
        return workRepository.findAllByExecutorId(id);
    }
}
