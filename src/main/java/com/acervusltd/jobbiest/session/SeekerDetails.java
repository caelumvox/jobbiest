package com.acervusltd.jobbiest.session;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.acervusltd.jobbiest.model.Seeker;

public class SeekerDetails extends User {

    private static final long serialVersionUID = 6910236996943103345L;
    
    private Seeker seeker;

    public SeekerDetails(Seeker seeker, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(seeker.getUsername(), seeker.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.seeker = seeker;
    }

    public Seeker getSeeker() {
        return seeker;
    }
}
