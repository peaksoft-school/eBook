//package kg.ebooks.eBook.db.service.impl;
//
//import kg.ebooks.eBook.db.domain.model.users.Client;
//import kg.ebooks.eBook.db.repository.ClientRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ClientDetailsServiceImpl implements UserDetailsService {
//    private final ClientRepository clientRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        Client client = clientRepository.findUserByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
//        return ClientDetailsImpl.build(client);
//    }
//}