package jms;

import models.Tv;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Сущность административного уведомления для передачи через брокер сообщений JMS.
 */
public class Alert implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operationType;
    private String message;
    private Tv tv;
    private String timestamp;

    public Alert() {
    }

    public Alert(String operationType, String message, Tv tv) {
        this.operationType = operationType;
        this.message = message;
        this.tv = tv;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tv getTv() {
        return tv;
    }

    public void setTv(Tv tv) {
        this.tv = tv;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] Тип события: %-15s | %s | Объект: %s",
                timestamp, operationType, message, tv != null ? tv.toString() : "N/A");
    }
}