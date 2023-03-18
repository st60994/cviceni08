package cz.upce.fei.cviceni01.domain;

import cz.upce.fei.cviceni01.dto.AppUserDto;
import liquibase.repackaged.org.apache.commons.lang3.builder.ToStringExclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class AppUser {

    public AppUser(String username, String password, Boolean active, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public AppUser(Long id, String username, String password, Boolean active, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Boolean active;

    @Column
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "author")
    @EqualsAndHashCode.Exclude
    private List<Task> tasks = Collections.emptyList();

    @ManyToMany(mappedBy = "users")
    @EqualsAndHashCode.Exclude
    private List<Role> roles = Collections.emptyList();

    public AppUserDto toDto() {
        return new AppUserDto(
                getId(),
                getUsername(),
                getPassword(),
                getActive(),
                getCreationDate(),
                getUpdateDate()
        );
    }
}
