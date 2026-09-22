import java.util.Objects;

/**
 * Класс, представляющий персону с именем и ростом.
 * Этот класс является базовым для других классов, таких как EMPLOYEE, ENGINEER и WORKER.
 */
public abstract class Person {
    private String name; // имя персоны
    private double height;  // рост персоны

    /**
     * Конструктор по умолчанию. Создает пустой объект персоны.
     */
    public Person() {
    }

    /**
     * Конструктор с параметрами. Создает объект персоны с указанными именем и ростом.
     *
     * @param name   имя персоны
     * @param height рост персоны
     */
    public Person(String name, double height) {
        this.name = name;
        this.height = height;
    }

    /**
     * Возвращает имя персоны.
     *
     * @return имя персоны
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя персоны.
     *
     * @param name имя персоны
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает рост персоны.
     *
     * @return рост персоны
     */
    public double getHeight() {
        return height;
    }

    /**
     * Устанавливает рост персоны.
     *
     * @param height рост персоны
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Возвращает строковое представление объекта персоны.
     *
     * @return строка в формате: "Имя - [имя], рост - [рост]"
     */
    @Override
    public abstract String toString();

    /**
     * Сравнивает текущий объект персоны с другим объектом на равенство.
     *
     * @param otherObject объект для сравнения
     * @return true, если объекты равны, иначе false
     */
   @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        Person other = (Person) otherObject;
        return Double.compare(other.height, height) == 0 && Objects.equals(name, other.name);
    }

    /**
     * Возвращает хэш-код объекта персоны.
     *
     * @return хэш-код
     */
    @Override
    public abstract int hashCode();

    /**
     * Проверяет, является ли объект персоны пустым.
     * Объект считается пустым, если имя и рост не заданы.
     *
     * @return true, если объект пустой, иначе false
     */
    public abstract boolean isEmpty();
}