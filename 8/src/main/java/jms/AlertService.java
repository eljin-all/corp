package jms;

/**
 * Интерфейс сервиса для отправки и извлечения JMS-уведомлений.
 */
public interface AlertService {

    /**
     * Отправить уведомление в очередь администратора.
     */
    void sendAlert(Alert alert);

    /**
     * Извлечь следующее уведомление из очереди администратора.
     */
    Alert getAlert();
}