package cz.upce.fei.cviceni01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private Long id;
    private String username;

    private String password;

    private Boolean active;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
}
