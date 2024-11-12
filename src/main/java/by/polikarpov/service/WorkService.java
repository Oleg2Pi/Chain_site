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

    public void saveWork(Work work) {
        workRepository.save(work);
    }

    public Integer getCounterWorks() {
        return workRepository.findAll().size();
    }

    public List<Work> getTopTenWorks(Integer executorId) { return workRepository.findTop10ByExecutorIdOrderByDateAddedDesc(executorId); }
}
