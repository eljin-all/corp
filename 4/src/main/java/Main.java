import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TvDao tvDao = context.getBean(TvDao.class);
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Выберите пункт меню: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showAll(tvDao);
                    break;
                case "2":
                    addNewTv(tvDao, scanner);
                    break;
                case "3":
                    editTv(tvDao, scanner);
                    break;
                case "4":
                    deleteTv(tvDao, scanner);
                    break;
                case "5":
                    searchByPrice(tvDao, scanner);
                    break;
                case "0":
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный пункт. Повторите ввод.");
            }
        }

        context.close();
    }

    private static void printMenu() {
        System.out.println("\n=== УПРАВЛЕНИЕ ТОВАРАМИ (ТЕЛЕВИЗОРЫ) ===");
        System.out.println("1. Вывести все записи");
        System.out.println("2. Добавить новый телевизор");
        System.out.println("3. Редактировать запись по ID");
        System.out.println("4. Удалить запись по ID");
        System.out.println("5. Поиск телевизоров с ценой не выше заданной");
        System.out.println("0. Выход");
    }

    private static void showAll(TvDao tvDao) {
        List<Tv> tvs = tvDao.findAll();
        if (tvs.isEmpty()) {
            System.out.println("База данных пуста.");
            return;
        }
        System.out.println("\nСписок телевизоров в БД:");
        for (Tv tv : tvs) {
            System.out.println(tv);
        }
    }

    private static void addNewTv(TvDao tvDao, Scanner scanner) {
        try {
            System.out.print("Введите бренд (например, Samsung): ");
            String brand = scanner.nextLine().trim();

            System.out.print("Введите модель (например, Q60A): ");
            String model = scanner.nextLine().trim();

            System.out.print("Введите технологию экрана (например, QLED): ");
            String screenTechnology = scanner.nextLine().trim();

            System.out.print("Введите диагональ экрана (дюймы, например 55.0): ");
            double diagonal = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Введите цену (например, 59990.0): ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            Tv tv = new Tv(brand, model, screenTechnology, diagonal, price);
            tvDao.insert(tv);
            System.out.println("Запись успешно добавлена.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода числовых параметров.");
        }
    }

    private static void editTv(TvDao tvDao, Scanner scanner) {
        try {
            System.out.print("Введите ID телевизора для редактирования: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            Tv tv = tvDao.findById(id);
            if (tv == null) {
                System.out.println("Телевизор с ID " + id + " не найден.");
                return;
            }

            System.out.println("Текущие данные: " + tv);

            System.out.print("Новый бренд (Enter чтобы оставить прежний): ");
            String brand = scanner.nextLine().trim();
            if (!brand.isEmpty()) tv.setBrand(brand);

            System.out.print("Новая модель (Enter чтобы оставить прежнюю): ");
            String model = scanner.nextLine().trim();
            if (!model.isEmpty()) tv.setModel(model);

            System.out.print("Новая технология (Enter чтобы оставить прежнюю): ");
            String tech = scanner.nextLine().trim();
            if (!tech.isEmpty()) tv.setScreenTechnology(tech);

            System.out.print("Новая диагональ (Enter чтобы оставить прежнюю): ");
            String diagStr = scanner.nextLine().trim();
            if (!diagStr.isEmpty()) tv.setScreenDiagonal(Double.parseDouble(diagStr));

            System.out.print("Новая цена (Enter чтобы оставить прежнюю): ");
            String priceStr = scanner.nextLine().trim();
            if (!priceStr.isEmpty()) tv.setPrice(Double.parseDouble(priceStr));

            tvDao.update(tv);
            System.out.println("Запись успешно обновлена.");
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод числа.");
        }
    }

    private static void deleteTv(TvDao tvDao, Scanner scanner) {
        try {
            System.out.print("Введите ID телевизора для удаления: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            int affectedRows = tvDao.delete(id);
            if (affectedRows > 0) {
                System.out.println("Запись успешно удалена.");
            } else {
                System.out.println("Запись с ID " + id + " не найдена.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ID.");
        }
    }

    private static void searchByPrice(TvDao tvDao, Scanner scanner) {
        try {
            System.out.print("Введите максимальную цену: ");
            double maxPrice = Double.parseDouble(scanner.nextLine().trim());

            List<Tv> results = tvDao.findByPriceLessThanEqual(maxPrice);
            if (results.isEmpty()) {
                System.out.println("Телевизоров с ценой до " + maxPrice + " руб. не найдено.");
                return;
            }

            System.out.println("\nРезультаты поиска:");
            for (Tv tv : results) {
                System.out.println(tv);
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод цены.");
        }
    }
}