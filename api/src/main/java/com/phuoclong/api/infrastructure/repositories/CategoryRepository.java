package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.infrastructure.Entitis.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Boolean existsByAccountIdAndFileIdAndCategoryName(UUID accountId, UUID directoryId, String folder);

    Optional<CategoryEntity> findByAccountIdAndFileIdAndCategoryName(UUID accountId, UUID directoryId, String folder);
}
