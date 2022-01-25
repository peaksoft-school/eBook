package kg.ebooks.eBook.db.domain.model.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 */
public enum Authority implements GrantedAuthority {
    CLIENT,
    VENDOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
