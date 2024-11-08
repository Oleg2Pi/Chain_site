package by.polikarpov.service;

import by.polikarpov.entity.ImagePerson;
import by.polikarpov.repository.ImagePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImagePersonService {

    @Autowired
    private ImagePersonRepository imagePersonRepository;

    public Optional<ImagePerson> getImage(Integer id) {
        return imagePersonRepository.findById(id);
    }
}
