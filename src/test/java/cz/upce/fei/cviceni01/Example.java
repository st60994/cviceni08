package cz.upce.fei.cviceni01;

import cz.upce.fei.cviceni01.domain.AppUser;

import java.time.LocalDateTime;

public final class Example {
    public static final AppUser EXISTING = new AppUser(100L, "userName", "pass",
            true, LocalDateTime.now(), LocalDateTime.now());

    private Example() {

    }


}
