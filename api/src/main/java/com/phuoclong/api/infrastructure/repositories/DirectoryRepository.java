package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.features.directories.response.FolderTrees;
import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.models.FolderSearch;
import com.phuoclong.api.infrastructure.models.MyFolderShare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface DirectoryRepository extends JpaRepository<DirectoryEntity, UUID> {

    Boolean existsByNameAndParentId(String name, UUID parentId);

    Optional<DirectoryEntity> findByIdAndLevel(UUID parentId, Integer level);

    Page<DirectoryEntity> findAllDirectoriesByParentIdAndAccountId(UUID parentId,UUID accountId, Pageable pageable);

    @Query(value = "With RECURSIVE  temp ( id , name, parentId, createdDate) as (" +
            "select id, name, parent_id, created_date from directory where id = :selectedId " +
            "UNION ALL " +
            "select d.id, d.name, d.parent_id, d.created_date from directory as d , temp as t where d.id = t.parentId " +
            ") " +
            "select id, name from temp order by createdDate asc ",nativeQuery = true)
    Collection<FolderTrees> getFolderTree(@Param("selectedId") String selectedId);

    @Query(value = "with recursive temp (id , name) as ( " +
            "select id, 'folder' as name from directory where id = :directoryId " +
            "union all " +
            "select d.id, 'folder' as name from directory d, temp t where d.parent_id = t.id " +
            "union all " +
            "select f.id, 'file' as name from file f, temp te where f.directory_id = te.id " +
            "union all " +
            "select c.id, 'tent' as name from content c, temp tem where c.file_id = tem.id " +
            ") " +
            "select * from temp ", nativeQuery = true)
    Collection<FolderTrees> getListDeleteItems(@Param("directoryId") String directoryId);

    Optional<DirectoryEntity> findByIdAndAccountId(UUID directoryId, UUID accountId);

    Page<DirectoryEntity> findAllDirectoriesByParentId(UUID parentId, Pageable pageable);

    @Query("SELECT d " +
            "from ShareEntity s left join DirectoryEntity d on s.fileId = d.id " +
            "where s.accountId = :accountId and d.level = :level and s.shareName = :shareName")
    Page<DirectoryEntity> findListShareWhenParentIdIsRoot(@Param("accountId") UUID accountId, @Param("level") Integer level,@Param("shareName") String shareName, Pageable pageable);

    @Query(value = "With recursive temp (id, name, parent_id, created_date) as( " +
            "select id, name, parent_id, created_date from directory where id = :directoryId " +
            "union all " +
            "select d.id, d.name, d.parent_id, d.created_date from directory d, temp t " +
            "where d.id = t.parent_id " +
            ") " +
            "select t.id, t.name from temp t inner join directory d on t.id = d.id inner join share s on t.id = s.file_id " +
            "where s.account_id = :accountId and s.share_name = :shareName", nativeQuery = true)
    Collection<FolderTrees> getFolderShareContainDirectory(String directoryId, String accountId, String shareName);

    @Query(value = "With recursive temp (id, name, parent_id, created_date) as ( " +
            "select id, name, parent_id, created_date from directory where id = :directoryId " +
            "union all " +
            "select d.id, d.name, " +
            "if((select count(*) from share s where s.file_id = d.id and s.account_id = :accountId) > 0, null, d.parent_id) as parent_id, " +
            "d.created_date from directory d, temp t " +
            "where d.id = t.parent_id " +
            ") " +
            "select id, name from temp order by created_date asc ", nativeQuery = true)
    Collection<FolderTrees> getShareFolderTree(@Param("directoryId") String directoryId,@Param("accountId") String accountId);

    @Query(value = "With recursive temp (id, name, parent_id, created_date) as ( " +
            "select id, name, parent_id, created_date from directory where id = :directoryId " +
            "union all " +
            "select d.id, d.name, " +
            "if((select count(*) from category c where c.file_id = d.id and c.account_id = :accountId) > 0, null, d.parent_id) as parent_id, " +
            "d.created_date from directory d, temp t " +
            "where d.id = t.parent_id " +
            ") " +
            "select id, name from temp order by created_date asc ", nativeQuery = true)
    Collection<FolderTrees> getFavoriteFolderTree(@Param("directoryId") String directoryId,@Param("accountId") String accountId);

    @Query("SELECT d " +
            "from CategoryEntity c left join DirectoryEntity d on c.fileId = d.id " +
            "where c.accountId = :accountId and d.level = :level and c.categoryName = :categoryName")
    Page<DirectoryEntity> findListFavoriteWhenParentIdIsRoot(@Param("accountId") UUID accountId, @Param("level") Integer level,@Param("categoryName") String categoryName, Pageable pageable);

    @Query(value = "With recursive temp (id, name, parent_id, created_date) as( " +
            "select id, name, parent_id, created_date from directory where id = :directoryId " +
            "union all " +
            "select d.id, d.name, d.parent_id, d.created_date from directory d, temp t " +
            "where d.id = t.parent_id " +
            ") " +
            "select t.id, t.name from temp t inner join directory d on t.id = d.id inner join category c on t.id = c.file_id " +
            "where c.account_id = :accountId and c.category_name = :categoryName", nativeQuery = true)
    Collection<FolderTrees> getFolderFavoriteContainDirectory(String directoryId, String accountId, String categoryName);

    @Query(value = "select d.id as id, d.name as name, d.createdBy as createdBy, d.createdDate as createdDate, " +
            "d.accountId as accountId, d.level as level, d.parentId as parentId, d.deleted as deleted, a.email as email, concat(a.firstName, ' ', a.lastName ) as receiver " +
            "from ShareEntity s " +
            "left join DirectoryEntity d on d.id = s.fileId " +
            "left join AccountEntity a on a.id = s.accountId " +
            "where d.createdBy = :accountId and d.level = :level order by d.id DESC ")
    Collection<MyFolderShare> getFolderInShareByAccount(String accountId, Integer level);


    @Query(value = "With recursive temp (id, name, parent_id, created_date) as ( " +
            "select id, name, parent_id, created_date from directory where id = :directoryId " +
            "union all " +
            "select d.id, d.name, " +
            "if(d.id = :selectId , null, d.parent_id) as parent_id, " +
            "d.created_date from directory d, temp t " +
            "where d.id = t.parent_id " +
            ") " +
            "select id, name from temp order by created_date asc ", nativeQuery = true)
    Collection<FolderTrees> getFolderTreeInMyShare(String directoryId, String selectId);

    @Query("SELECT d.id AS id, d.name AS name, d.level as level " +
            "FROM DirectoryEntity d " +
            "WHERE d.createdBy = :accountId AND d.parentId <> :rootId AND d.name LIKE CONCAT('%', :keySearch, '%') ")
    Page<FolderSearch> searchFolderWithKeySearch(String accountId, String keySearch, UUID rootId, Pageable pageable);
}
