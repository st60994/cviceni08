package cz.upce.fei.cviceni01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserInputQLDto {
    private Long id;
    private String username;

    private String password;

    private Boolean active;

    private String creationDate;

    private String updateDate;

    public AppUserDto toAppUserDto() {
        return new AppUserDto(
                getId(),
                getUsername(),
                getPassword(),
                getActive(),
                LocalDateTime.parse(getCreationDate()),
                LocalDateTime.parse(getUpdateDate())
        );
    }
}

