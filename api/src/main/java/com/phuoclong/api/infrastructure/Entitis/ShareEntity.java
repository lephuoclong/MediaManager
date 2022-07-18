package com.phuoclong.api.infrastructure.Entitis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "share")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ShareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @Type(type = "uuid-char")
    UUID accountId;

    @Column(nullable = false)
    @Type(type = "uuid-char")
    UUID fileId;

    @Column(nullable = false, columnDefinition = "nvarchar(50)")
    String shareName;
}
