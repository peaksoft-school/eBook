package kg.ebooks.eBook.db.domain.dto.book;

import kg.ebooks.eBook.db.domain.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    private Long id;
    private String search;
    private Type type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchDto searchDto = (SearchDto) o;
        return Objects.equals(search, searchDto.search) && type == searchDto.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(search, type);
    }
}
