package com.ua.project.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {
    private final CustomUserRepository customUserRepository;

    public Optional<CustomUser> findByUsername(String username) {
        return customUserRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        CustomUser customUser = findByUsername(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("user is not exist: " + username);
                });

        return new org.springframework.security.core.userdetails.User(
                customUser.getUsername(),
                customUser.getPassword(),
                customUser.getRoles().stream().map(role -> (GrantedAuthority) role::getName)
                        .toList()
        );
    }
}
