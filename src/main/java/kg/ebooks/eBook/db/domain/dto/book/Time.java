package kg.ebooks.eBook.db.domain.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * created by Beksultan Mamatkadyr uulu
 * 4/2/22
 * Friday 17:22
 * hello world
 */
@Getter @Setter
public class Time {
    private byte hour;
    private byte minute;
    private byte second;

    public LocalTime makeLocalTime() {
        return LocalTime.of(hour, minute, second);
    }
}
