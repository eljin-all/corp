import java.util.ArrayList;
import java.util.Scanner;

/**
 * Основной класс программы для управления списком объектов классов персона, студент, преподаватель, сотрудник.
 * Позволяет добавлять, удалять, выводить в консоль, сравнивать элементы.
 */
public class Main {

    public static final int LIST_SHIFT = 1; // константа, нужная при переводе номеров списка в индексы
    public static final int LIST_FIRST_ELEMENT = 0; // константа, нужная для обозначения первого элемента списка
    public static final String DASH = "-".repeat(27); // константа, нужная, чтобы придать тексту читаемость
    public static final boolean DEL_FLAG = true; // константа, нужная для метода listOperations
    public static final int MIN_HEIGHT = 10; //минимальный рост в см
    public static final int MAX_HEIGHT = 500; //максимальный рост в см
    public static final int MIN_HOURS_PROJECTS_SALARY = 0; //минимальные учебное время, количество классов и зарплата

    /**
     * Метод для красивого вывода объектов.
     *
     * @param list список объектов `PERSON`.
     */
    public static void listPrint(ArrayList<Person> list) {
        System.out.println(DASH);
        for (int i = LIST_FIRST_ELEMENT; i < list.size(); i++) {
            Person s = list.get(i);
            String className = "персона"; // По умолчанию
            if (s instanceof Employee) {
                className = "служащий";
            } else if (s instanceof Teacher) {
                className = "учителя";
            } else if (s instanceof Student) {
                className = "студент";
            }
            System.out.println("Объект " + (i + LIST_SHIFT) + " - " + className + ": " + (s.isEmpty() ? "Пустой объект" : s));
        }
    }


    /**
     * Это метод, позволяющий создавать объекты классов
     *
     * @param persons    это список объектов
     * @param objectName это имя объекта для определения добавляемого класса
     */
    public static void objectCreating(ArrayList<Person> persons, String objectName, String emptiness) {
        Enums.CLASSES methodChoice = Enums.CLASSES.fromString(objectName);
        Enums.Fullness emptyChoice = Enums.Fullness.fromString(emptiness);
        if ((methodChoice == null) || (emptyChoice == null)) {
            System.out.println(DASH);
            System.out.println(DASH);
            System.out.println("Выберите один из предложенных вариантов!");
            return;
        }
        switch (emptyChoice) {
            case EMPTY:
                System.out.println(DASH);
                switch (methodChoice) {
                    case STUDENT:
                        System.out.println("Пустой объект студента был добавлен.");
                        persons.add(new Student());
                        break;
                    case TEACHER:
                        System.out.println("Пустой объект учителя был добавлен.");
                        persons.add(new Teacher());
                        break;
                    case EMPLOYEE:
                        System.out.println("Пустой объект служащего был добавлен.");
                        persons.add(new Employee());
                        break;
                }
                break;
            case FULL:
                Scanner in = new Scanner(System.in);
                int height;
                int numericalAttribute;
                String stringAttribute;
                String attributeMessage;
                String errorMessage;
                System.out.println(DASH);
                System.out.print("Введите имя: ");
                String name = in.nextLine().trim();
                System.out.println(DASH);
                System.out.print("Введите рост(в см): ");
                if (!in.hasNextDouble()) {
                    System.out.println(DASH);
                    System.out.println("Введите число!");
                    in.nextLine();
                    return;
                }
                int newInput = in.nextInt();
                if (newInput >= MIN_HEIGHT && newInput <= MAX_HEIGHT) {
                    height = newInput;
                } else {
                    System.out.println(DASH);
                    System.out.println("Рост не может быть таким!");
                    in.nextLine();
                    return;
                }

                    System.out.println(DASH);
                    in.nextLine();
                    if (objectName.equals("Student")) {
                        System.out.print("Введите группу здоровья студента: ");
                        stringAttribute = in.nextLine().trim();
                        attributeMessage = "Введите количество учебных часов студента: ";
                        errorMessage = "Студент не может столько учиться!";
                    } else if (objectName.equals("Teacher")) {
                        System.out.print("Введите квалификацию учителя: ");
                        stringAttribute = in.nextLine().trim();
                        attributeMessage = "Введите количество классов учителя: ";
                        errorMessage = "Учитель не может руководить столькими классами!";
                    } else {
                        System.out.print("Введите рабочее место служащего: ");
                        stringAttribute = in.nextLine().trim();
                        attributeMessage = "Введите зарплату служащего: ";
                        errorMessage = "Служащий не может столько получать";
                    }
                    System.out.println(DASH);
                    System.out.print(attributeMessage);
                    if (!in.hasNextInt()) {
                        System.out.println(DASH);
                        System.out.println("Введите число!");
                        in.nextLine();
                        return;
                    }
                    newInput = in.nextInt();
                    if (newInput >= MIN_HOURS_PROJECTS_SALARY) {
                        numericalAttribute = newInput;
                    } else {
                        System.out.println(DASH);
                        System.out.println(errorMessage);
                        in.nextLine();
                        return;
                    }
                    in.nextLine();
                    System.out.println(DASH);
                    if (objectName.equals("Student")) {
                        persons.add(new Student(name, height, stringAttribute, numericalAttribute));
                        System.out.println("Объект студента был создан.");
                    } else if (objectName.equals("Teacher")) {
                        persons.add(new Teacher(name, height, stringAttribute, numericalAttribute));
                        System.out.println("Объект учителя был создан.");
                    } else {
                        persons.add(new Employee(name, height, stringAttribute, numericalAttribute));
                        System.out.println("Объект служащего был создан.");
                    }
                }

    }

    /**
     * @param persons список объектов с классами
     * @param del     флаг, дающий понять, будет удаление или сравнение
     */
    public static void listOperations(ArrayList<Person> persons, boolean del) {
        Scanner in = new Scanner(System.in);
        int realIndex;
        int secondIndex;
        System.out.println(DASH);
        if (del) {
            System.out.print("Введите номер объекта, который вы хотите удалить: ");
        } else {
            System.out.print("Введите первый номер объекта, который вы хотите сравнить: ");
        }
        if (!in.hasNextInt()) {

            System.out.println(DASH);
            System.out.println("Введите число!");
            in.nextLine();
            return;
        }
        int checkingIndex = in.nextInt();
        if ((LIST_FIRST_ELEMENT <= checkingIndex - LIST_SHIFT) && (checkingIndex <= persons.size())) {
            realIndex = checkingIndex - LIST_SHIFT;
        } else {
            System.out.println(DASH);
            System.out.println("Данного элемента нет в списке!");
            in.nextLine();
            return;
        }
        if (del) {
            persons.remove(realIndex);
            System.out.println(DASH);
            System.out.println("Объект удален");
            in.nextLine();
        } else {
            System.out.print("Введите первый номер объекта, который вы хотите сравнить: ");
            if (!in.hasNextInt()) {

                System.out.println(DASH);
                System.out.println("Введите число!");
                in.nextLine();
                return;
            }
            checkingIndex = in.nextInt();
            if ((LIST_FIRST_ELEMENT <= checkingIndex - LIST_SHIFT) && (checkingIndex <= persons.size())) {
                secondIndex = checkingIndex - LIST_SHIFT;
            } else {
                System.out.println(DASH);
                System.out.println("Данного элемента нет в списке!");
                in.nextLine();
                return;
            }
            System.out.println(DASH);
            if (realIndex == secondIndex) {
                System.out.println("Вы два раза ввели один и тот же индекс!");
            } else if (persons.get(realIndex).equals(persons.get(secondIndex)) && persons.get(realIndex).hashCode() == persons.get(secondIndex).hashCode()) {
                System.out.println("Объекты равны");
            } else {
                System.out.println("Объекты не равны");
            }
        }
    }

    /**
     * Главный метод программы.
     * Реализует консольное меню для работы со списком объектов разных классов.
     * Позволяет добавлять, удалять, сравнивать объекты.
     *
     * @param args аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Person> persons = new ArrayList<>();
        System.out.println(DASH);
        System.out.println("Это программа для создания классов вида: студент, учитель, служащий");

        while (true) {
            System.out.println(DASH);
            System.out.print(
                    """
                            1. Добавить новый элемент
                            2. Удалить элемент по индексу
                            3. Вывод всех элементов в консоль
                            4. Сравнение двух элементов на равенство
                            5. Завершение работы приложения
                            Выберите действие:""");
            String quest = in.next().trim();
            Enums.MainMenu option = Enums.MainMenu.fromString(quest);
            if (option == null) {
                System.out.println(DASH);
                System.out.println("Выберите один из предложенных вариантов!");
                continue;
            }
            switch (option) {
                case ADD_NEW:
                    System.out.println(DASH);
                    System.out.print(
                            """
                                    1. Студент
                                    2. Учитель
                                    3. Служащий
                                    Выберите Класс:""");
                    String chosenClass = in.next().trim();
                    Enums.CLASSES choice = Enums.CLASSES.fromString(chosenClass);
                    if (choice == null) {
                        System.out.println(DASH);
                        System.out.println("Выберите один из предложенных вариантов!");
                        continue;
                    }
                    System.out.println(DASH);
                    System.out.print(
                            """
                                    1. Добавить пустой объект
                                    2. Добавить заполненный объект
                                    Выберите действие:""");
                    String full = in.next().trim();
                    Enums.Fullness fullChoice = Enums.Fullness.fromString(full);
                    if (fullChoice == null) {
                        System.out.println(DASH);
                        System.out.println("Выберите один из предложенных вариантов!");
                        continue;
                    }
                    objectCreating(persons, chosenClass, full);
                    break;
                case DEL:
                    if (persons.isEmpty()) {
                        System.out.println(DASH);
                        System.out.println("Вы еще не создали объектов!");
                        break;
                    }
                    listOperations(persons, DEL_FLAG);
                    break;
                case PRINT:
                    if (persons.isEmpty()) {
                        System.out.println(DASH);
                        System.out.println("Вы еще не создали объектов!");
                        break;
                    }
                    listPrint(persons);
                    break;
                case COMPAR:
                    if (persons.isEmpty()) {
                        System.out.println(DASH);
                        System.out.println("Вы еще не создали объектов!");
                        break;
                    }
                    if (persons.size() == 1) {
                        System.out.println(DASH);
                        System.out.println("Вы создали только один объект!");
                        break;
                    }
                    listOperations(persons, !DEL_FLAG);
                    break;
                case EXIT:
                    in.close();
                    return;
            }
        }
    }
}