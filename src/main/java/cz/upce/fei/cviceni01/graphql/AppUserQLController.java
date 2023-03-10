package cz.upce.fei.cviceni01.graphql;

import cz.upce.fei.cviceni01.dto.AppUserDto;
import cz.upce.fei.cviceni01.service.AppUserService;
import cz.upce.fei.cviceni01.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class AppUserQLController {

    private final AppUserService appUserService;

    @QueryMapping
    public AppUserDto appUser(@Argument final Long id) throws ResourceNotFoundException {
        return appUserService.findById(id).toDto();
    }

    @MutationMapping
    public AppUserDto createAppUser(@Argument final ){
        appUserService.c
    }

}
