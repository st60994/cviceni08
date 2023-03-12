package cz.upce.fei.cviceni01.dto;

import cz.upce.fei.cviceni01.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppUserInput {
    private String username;

    private String password;

    private Boolean active;

    private String creationDate;

    private String updateDate;

    public AppUser toAppUser() {
        return new AppUser(
                getUsername(),
                getPassword(),
                getActive(),
                LocalDateTime.parse(getCreationDate()),
                LocalDateTime.parse(getUpdateDate())
        );
    }
}

