package cz.upce.fei.cviceni01.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.upce.fei.cviceni01.dto.TaskResponseDtoV1;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDateTime creationDate;
    @Column
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private AppUser author;


    public TaskResponseDtoV1 toDto(){
        return new TaskResponseDtoV1(
                getId(),
                getTitle(),
                getDescription(),
                getCreationDate(),
                getUpdateDate()
        );
    }
}
