package kg.ebooks.eBook.db.domain.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.time.Month;

/**
 * created by Beksultan Mamatkadyr uulu
 * 22/2/22
 * Tuesday 14:54
 * hello world
 */
@Getter
@Setter
public class Date {
    private int day;
    private Month month;
    private int year;
}