package com.creacionusuario.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "email",unique = true)
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;
    @Column(name = "password")
    private String password;
    @Column(name = "created")
    private Date created;
    @Column(name = "modified")
    private Date modified;
    @Column(name = "last_login")
    private Date lastLogin;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "token")
    private String token;

    @PrePersist
    protected void onCreate(){
        created = new Date(System.currentTimeMillis());
        modified = created;
        lastLogin = created;
    }
    @PreUpdate
    protected void onUpdate(){
        modified = new Date(System.currentTimeMillis());
    }
}
