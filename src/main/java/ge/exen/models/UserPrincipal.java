package ge.exen.models;

import java.security.Principal;

public class UserPrincipal implements Principal {

    long id;

    public UserPrincipal(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return ""+id;
    }

    public long getId() {
        return id;
    }

}
