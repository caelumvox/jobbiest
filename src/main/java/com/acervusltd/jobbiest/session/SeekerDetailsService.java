package com.acervusltd.jobbiest.session;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.acervusltd.jobbiest.db.SeekersTableGateway;
import com.acervusltd.jobbiest.model.Seeker;

public class SeekerDetailsService implements UserDetailsService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SeekerDetailsService.class);
    
    @Autowired
    private SeekersTableGateway seekerTableGateway;
    
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.trace("Fetching details for seeker [{}]", username);
        
        Seeker seeker = seekerTableGateway.getSeekerByUsername(username);
        
        if (seeker == null) {
            throw new UsernameNotFoundException("Unable to find seeker with username " + username);
        }
        
        return new SeekerDetails(seeker, true, true, true, true, getGrantedAuthorities(seeker));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Seeker seeker){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        authorities.add(new SimpleGrantedAuthority("ROLE_SEEKER"));

        return authorities;
    }
    
    public static Seeker getSeeker() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Seeker seeker = null;
        if (principal instanceof SeekerDetails) {
            seeker = ((SeekerDetails) principal).getSeeker();
        } else {
            throw new RuntimeException("Unable to establish context.");
        }

        return seeker;
    }
}
