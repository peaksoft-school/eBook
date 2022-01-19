//package kg.ebooks.eBook.db.service.impl;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
//import kg.ebooks.eBook.db.domain.model.users.Client;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//
//public class ClientDetailsImpl {
//
//    private static final long serialVersionUID = 1L;
//
//    private Long id;
//    private String email;
//    @JsonIgnore
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public ClientDetailsImpl(Long id, String email, String password,
//                           Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static ClientDetailsImpl build(Client client) {
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        return new ClientDetailsImpl(client.getClientId(), client.getEmail(),
//                client.getName(),)
//
//
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRoleName))
//                .collect(Collectors.toList());
//   }
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities()
//
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        ClientDetailsImpl other = (ClientDetailsImpl) obj;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id))
//            return false;
//        return true;
//    }
//}