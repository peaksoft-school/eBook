package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.repository.SecurityRepository;
import kg.ebooks.eBook.db.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final SecurityRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findAuthenticationInfoByEmail(username)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("user with email = %s not found", username)
                ));
    }
}
