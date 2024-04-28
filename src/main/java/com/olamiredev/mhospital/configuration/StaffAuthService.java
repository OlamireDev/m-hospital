package com.olamiredev.mhospital.configuration;

import com.olamiredev.mhospital.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffAuthService implements UserDetailsService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        staffRepository.findByUuid(uuid).orElseThrow(()-> new UsernameNotFoundException(
                uuid + " was not found"));
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        return new User(
                uuid, "" ,roles);
    }
}


