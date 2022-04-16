package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DirectoryRepository extends JpaRepository<DirectoryEntity, UUID> {

    Boolean existsByNameAndParentId(String name, UUID parentId);

    Optional<DirectoryEntity> findByIdAndLevel(UUID parentId, Integer level);
}
