package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.dto.book.SearchDto;

import java.util.List;

public interface SearchService {

    List<SearchDto> findAll(String search) ;

}
