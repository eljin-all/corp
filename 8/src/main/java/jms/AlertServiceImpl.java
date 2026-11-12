package jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * Реализация сервиса обмена сообщениями через JmsTemplate.
 */
public class AlertServiceImpl implements AlertService {

    private final JmsTemplate jmsTemplate;

    public AlertServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendAlert(final Alert alert) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(alert);
            }
        });
    }

    @Override
    public Alert getAlert() {
        try {
            Message message = jmsTemplate.receive();
            if (message == null) {
                return null;
            }
            ObjectMessage receivedMessage = (ObjectMessage) message;
            return (Alert) receivedMessage.getObject();
        } catch (JMSException jmsException) {
            throw JmsUtils.convertJmsAccessException(jmsException);
        }
    }
}