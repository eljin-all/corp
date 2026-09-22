/**
 * Это класс для кейсов в меню
 */
public class Enums {
    public enum MainMenu {
        ADD_NEW("1"), // первый пункт в главном меню
        DEL("2"), // второй пункт в главном меню
        PRINT("3"), // третий пункт в главном меню
        COMPAR("4"), // четвертый пункт в главном меню
        EXIT("5");// пятый пункт в главном меню

        private final String codeMenu;

        /**
         * Конструктор, привязывающий строковый код к пункту меню.
         *
         * @param code строковое представление команды.
         */
        MainMenu(String code) {
            this.codeMenu = code;
        }

        /**
         * @return строковый код команды.
         */
        public String getCode() {
            return codeMenu;
        }

        /**
         * Поиск элемента `MainMenu` по строковому коду.
         *
         * @param code код, введённый пользователем.
         * @return соответствующий пункт меню или `null`, если код не найден.
         */
        public static MainMenu fromString(String code) {
            for (MainMenu option : MainMenu.values()) {
                if (option.getCode().equals(code)) {
                    return option;
                }
            }
            return null;
        }
    }

    /**
     * Перечисление, предоставляющее поля для выбора редактирования/сортировки.
     */
    public enum CLASSES {
        STUDENT("1"), // студента - второй класс для выбора
        TEACHER("2"), // учителя - третий класс для выбора
        EMPLOYEE("3"); // служащий - четвертый класс для выбора

        private final String codeField;

        /**
         * Конструктор, привязывающий строковый код к пункту меню.
         *
         * @param code строковое представление команды.
         */
        CLASSES(String code) {
            this.codeField = code;
        }

        /**
         * @return строковый код команды.
         */
        public String getCode() {
            return codeField;
        }

        /**
         * Поиск элемента `Fields` по строковому коду.
         *
         * @param code код, введённый пользователем.
         * @return соответствующий пункт меню или `null`, если код не найден.
         */
        public static CLASSES fromString(String code) {
            for (CLASSES option : CLASSES.values()) {
                if (option.getCode().equals(code)) {
                    return option;
                }
            }
            return null;
        }


    }

    /**
     * Перечисление, предоставляющее направление сортировки.
     */
    public enum Fullness {
        EMPTY("1"), // добавление пустого объекта
        FULL("2"); // добавление заполненного объекта

        private final String codeSort;

        /**
         * Конструктор, привязывающий строковый код к пункту меню.
         *
         * @param code строковое представление команды.
         */
        Fullness(String code) {
            this.codeSort = code;
        }

        /**
         * @return строковый код команды.
         */
        public String getCode() {
            return codeSort;
        }

        /**
         * Поиск элемента `SortingType` по строковому коду.
         *
         * @param code код, введённый пользователем.
         * @return соответствующий пункт меню или `null`, если код не найден.
         */
        public static Fullness fromString(String code) {
            for (Fullness option : Fullness.values()) {
                if (option.getCode().equals(code)) {
                    return option;
                }
            }
            return null;
        }
    }
}
