package org.pincio.games.dto;

import org.pincio.games.model.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserPrincipal implements UserDetails {
    private Person person;

    public MyUserPrincipal(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority(person.getRole()));
        //return user.getRole().stream().map(role -> new SimpleGrantedAuthority("Role_"+role))
          //      .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {

        return null;
    }

    @Override
    public String getUsername() {

        return person.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return person.isValid();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
