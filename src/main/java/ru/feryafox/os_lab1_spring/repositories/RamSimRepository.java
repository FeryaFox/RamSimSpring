package ru.feryafox.os_lab1_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.feryafox.os_lab1_spring.models.RamSimEntity;

import java.util.Optional;

public interface RamSimRepository extends JpaRepository<RamSimEntity, Long> {
    void deleteByUuid(String uuid);
    Optional<RamSimEntity> findByUuid(String uuid);
}