package com.mybills.mvc.service;

import com.mybills.mvc.domain.BillUser;
import com.mybills.mvc.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Padonag on 28.12.2014.
 */
@Service("userDetailService")
public class BillUserDetailService implements UserDetailsService {

    @Autowired
    private IBillUserService billUserService;

    @Override
    public UserDetails loadUserByUsername( final String userLogin) throws UsernameNotFoundException {
        BillUser user = billUserService.getUserByLogin(userLogin);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());
        return buildUserForAuthentication(user, authorities);
    }

    // Converts BillUser  to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(BillUser user, List<GrantedAuthority> authorities) {
        return new User(user.getLogin(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles){
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
//        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return new ArrayList<GrantedAuthority>(setAuths);
    }
}
