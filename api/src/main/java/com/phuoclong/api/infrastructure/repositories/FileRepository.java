package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import com.phuoclong.api.infrastructure.models.FileSearch;
import com.phuoclong.api.infrastructure.models.MyFileShare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> {

    Page<FileEntity> findAllByDirectoryId(UUID directoryId, Pageable pageable);

    Optional<FileEntity> findByIdAndAccountId(UUID id, UUID accountId);

    Page<FileEntity> findAllByDirectoryIdAndAccountId(UUID directoryId, UUID accountId, Pageable pageable);

    @Query("SELECT f " +
            "from ShareEntity s left join FileEntity f on s.fileId = f.id " +
            "where s.accountId = :accountId and f.type = :fileType and s.shareName = :shareName")
    Page<FileEntity> findAllFileInShareRoot(UUID accountId, String fileType, String shareName, Pageable pageable);

    @Query("SELECT f " +
            "from CategoryEntity c left join FileEntity f on c.fileId = f.id " +
            "where c.accountId = :accountId and f.type = :typeOfFile and c.categoryName = :categoryName")
    Page<FileEntity> findAllFileInFavoriteRoot(UUID accountId, String typeOfFile, String categoryName, Pageable pageable);

    @Query("SELECT f.id AS id, f.displayName as displayName, f.name as name, f.size as size, f.type as type, f.directoryId as directoryId, " +
            "concat(a.firstName, ' ', a.lastName) as receiver, a.email as email " +
            "from ShareEntity s " +
            "left join FileEntity f on f.id = s.fileId " +
            "left join DirectoryEntity d on d.id = f.directoryId " +
            "left join AccountEntity a on a.id = s.accountId " +
            "where f.createdBy = :accountId and s.shareName = :shareName and d.level = :level order by f.displayName asc ")
    Collection<MyFileShare> getListFileInMyShare(String accountId, String shareName, Integer level);


    @Query("SELECT f.id AS id, f.name AS name, f.displayName as displayName, f.type as type, f.size as size, f.accountId as accountId, f.directoryId as directoryId " +
            "FROM FileEntity f " +
            "WHERE f.createdBy = :accountId AND f.type = :fileType AND f.name LIKE CONCAT('%', :keySearch, '%') ")
    Page<FileSearch> searchFileWithKeySearch(String accountId,String fileType, String keySearch, Pageable pageable);
}
