package com.phuoclong.api.infrastructure.Entitis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "file", indexes = {
        @Index(name="IX_FILE_NAME",columnList = "name")
})
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class FileEntity extends BaseAudiEntity{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    UUID id;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String name;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String displayName;

    @Column(nullable = false)
    Long size;

    @Column
    @Type(type = "uuid-char")
    UUID accountId;

    @Column
    @Type(type = "uuid-char")
    UUID directoryId;

    @Column(nullable = false)
    String type;

    @Column(nullable = false)
    Integer deleted = 0;
}
