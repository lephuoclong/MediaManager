package com.phuoclong.api.infrastructure.Entitis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "account",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
},indexes = {
        @Index(name = "IX_ACCOUNT_EMAIL", columnList = "email", unique = true),
        @Index(name = "IX_ACCOUNT_FIRSTNAME", columnList = "firstName", unique = true),
        @Index(name = "IX_ACCOUNT_LASTNAME", columnList = "lastName", unique = true)

})
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class AccountEntity extends BaseAudiEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    UUID id;

    @Column(length = 50,columnDefinition = "nvarchar(50)")
    String firstName;

    @Column(length = 50,columnDefinition = "nvarchar(50)")
    String lastName;

    @Column(length = 50, columnDefinition = "nvarchar(50)")
    String username;

    @Column(length = 200,nullable = false,columnDefinition = "nvarchar(200)")
    String email;

    @Column(length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    String password;

    Instant checkInTime;

    //    0: sign in, 1: active, -1: deleted, 2 block
    @Column(nullable = false)
    Integer status = 0;

    @Column(columnDefinition = "nvarchar(500)")
    String token;

}
