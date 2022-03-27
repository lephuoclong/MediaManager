package com.phuoclong.api.infrastructure.Entitis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="directory", indexes = {
        @Index(name="IX_DIRECTORY_NAME" ,columnList = "name")
})
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class DirectoryEntity extends BaseAudiEntity{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    UUID id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( unique = true, nullable = false, insertable = false, updatable = false)
    private Long flagParent;

    @Column(length = 50,nullable = false,columnDefinition = "nvarchar(50)")
    String name;

    @Column(nullable = false, columnDefinition = "integer")
    Integer level = 0;

    @Column(nullable = false, columnDefinition = "bigint")
    Long parentId = 0L;

    @Column(nullable = false)
    @Type(type = "uuid-char")
    UUID accountId;

    @Column
    Integer deleted = 0;
}
