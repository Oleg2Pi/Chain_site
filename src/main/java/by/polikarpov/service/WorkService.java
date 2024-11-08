package by.polikarpov.service;

import by.polikarpov.entity.Work;
import by.polikarpov.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    public void saveWork(Work work) {
        workRepository.save(work);
    }
}
