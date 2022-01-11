package kg.ebooks.eBook.db.domain.model.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * created by Beksultan Mamatkadyr uulu
 * project : eBook
 * 9/1/22
 * Sunday 10:16
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
