package kg.ebooks.eBook.db.service.impl;

import kg.ebooks.eBook.db.repository.BookRepository;
import kg.ebooks.eBook.db.service.BookGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * created by Beksultan Mamatkadyr uulu
 * 6/2/22
 * Sunday 18:32
 * hello world
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookGetServiceImpl implements BookGetService {

    private final BookRepository bookRepository;

}
