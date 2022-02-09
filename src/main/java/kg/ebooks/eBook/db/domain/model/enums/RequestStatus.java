package kg.ebooks.eBook.db.domain.model.enums;

/**
 * created by Beksultan Mamatkadyr uulu
 * 1/2/22
 * Tuesday 15:04
 * hello world
 */
public enum RequestStatus {
    ACCEPTED,
    DENIED,
    INPROGRESS;

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
