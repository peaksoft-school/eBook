package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;


@Service
@RequiredArgsConstructor
@Slf4j
public class ClientDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + email));

        return new org.springframework.security
                .core.userdetails.User(
                client.getEmail(),
                client.getName(),
                client.getAuthenticationInfo().isEnabled(),
                true,
                true,
                true,
                getAuthorities("USER")
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singleton((new SimpleGrantedAuthority(role)));
    }
}