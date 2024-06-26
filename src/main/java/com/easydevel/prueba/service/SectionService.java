package com.easydevel.prueba.service;

import com.easydevel.prueba.model.Section;
import com.easydevel.prueba.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class SectionService {

    private static final String REDIS_PREFIX = "section:";

    @Autowired
    private RedisTemplate<String, Section> redisTemplate;

    @Autowired
    private SectionRepository sectionRepository;

    public Optional<Section> getSection(Long id) {
        String redisKey = REDIS_PREFIX + id;
        Section section = redisTemplate.opsForValue().get(redisKey);
        if (section != null) {
            return Optional.of(section);
        }

        doSlowTask();
        Optional<Section> sectionOptional = sectionRepository.findById(id);
        if (sectionOptional.isPresent()) {
            redisTemplate.opsForValue().set(redisKey, sectionOptional.get(), 1, TimeUnit.HOURS);
        }

        return sectionOptional;
    }

    public List<Section> getSections() {
        return sectionRepository.findAll();
    }

    public Section addSection(Section section) {
        return sectionRepository.save(section);
    }

    private void doSlowTask() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
