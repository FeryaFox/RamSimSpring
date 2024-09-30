package ru.feryafox.ram_sim_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.feryafox.ram_sim_spring.models.RamSimEntity;

import java.util.Optional;

public interface RamSimRepository extends JpaRepository<RamSimEntity, Long> {
    void deleteByUuid(String uuid);
    Optional<RamSimEntity> findByUuid(String uuid);
}