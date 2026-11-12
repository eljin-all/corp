package jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Консольное приложение для извлечения административных уведомлений
 * из очереди брокера ActiveMQ (пункт 3 задания).
 */
public class JmsReceiverApp {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   ИЗВЛЕЧЕНИЕ АДМИНИСТРАТИВНЫХ СООБЩЕНИЙ ИЗ ОЧЕРЕДИ (tv.admin.queue)      ");
        System.out.println("==========================================================================\n");

        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("classpath:config/jmsConfig.xml")) {

            AlertService alertService = context.getBean("alertService", AlertService.class);

            int count = 0;
            while (true) {
                Alert alert = alertService.getAlert();
                if (alert == null) {
                    break;
                }
                count++;
                System.out.println(count + ". " + alert);
            }

            if (count == 0) {
                System.out.println("Очередь пуста — новых уведомлений нет.");
            } else {
                System.out.println("\nВсего успешно извлечено сообщений из очереди: " + count);
            }
        }

        System.out.println("==========================================================================");
    }
}