package kg.ebooks.eBook.db.domain.model.users;

import kg.ebooks.eBook.db.domain.model.enums.Authority;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 9/1/22
 * Sunday 10:13
 */
@Entity
@Table(name = "authenticatoin_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationInfo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authenticationInfoId;
    private Authority authority;
    private String password;
    @Column(unique = true)
    private String email;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}
