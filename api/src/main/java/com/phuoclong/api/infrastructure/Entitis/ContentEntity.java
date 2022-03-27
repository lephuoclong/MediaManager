package com.phuoclong.api.infrastructure.Entitis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.util.UUID;

@Entity
@Table(name = "content")
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ContentEntity extends BaseAudiEntity{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    UUID id;

    @Column(nullable = false, columnDefinition="BLOB")
    @Lob
    Blob content;

    @Column(nullable = false)
    @Type(type = "uuid-char")
    UUID shareId;

    @Column(nullable = false)
    @Type(type = "uuid-char")
    UUID fileId;

    @Column(nullable = false)
    Long size;

    @Column(nullable = false)
    String type;


}
