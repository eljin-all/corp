import java.util.Objects;

/**
 * Класс, представляющий служащего.
 * Наследует свойства класса PERSON и добавляет информацию о месте работы и зарплате.
 */
public class Employee extends Person {
    private String workPlace; // место работы служащего
    private double salary;       // зарплата служащего

    /**
     * Конструктор по умолчанию. Создает пустой объект служащего.
     */
    public Employee() {
    }

    /**
     * Конструктор с параметрами. Создает объект служащего с указанными именем, ростом, местом работы и зарплатой.
     *
     * @param name      имя служащего
     * @param height    рост служащего
     * @param workPlace место работы служащего
     * @param salary    зарплата служащего
     */
    public Employee(String name, double height, String workPlace, double salary) {
        super(name, height);
        this.workPlace = workPlace;
        this.salary = salary;
    }

    /**
     * Возвращает место работы служащего.
     *
     * @return место работы
     */
    public String getWorkPlace() {
        return workPlace;
    }

    /**
     * Устанавливает место работы служащего.
     *
     * @param workPlace место работы
     */
    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    /**
     * Возвращает зарплату служащего.
     *
     * @return зарплата
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Устанавливает зарплату служащего.
     *
     * @param salary зарплата
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Возвращает строковое представление объекта служащего.
     *
     * @return строка в формате: "Имя - [имя], рост - [рост], место работы - [место работы], зарплата - [зарплата]"
     */
    @Override
    public String toString() {
        return "Имя - " + (super.getName() != null ? super.getName() : "не указано") +
                ", рост - " + (super.getHeight() != 0 ? super.getHeight() : "не указан") +
                ", место работы - " + (workPlace != null ? workPlace : "не указано") +
                ", зарплата - " + (salary != 0 ? salary : "не указана");
    }

    /**
     * Сравнивает текущий объект служащего с другим объектом на равенство.
     *
     * @param otherObject объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object otherObject) {
        if (!super.equals(otherObject)) return false;
        Employee other = (Employee) otherObject;
        return salary == other.salary && Objects.equals(workPlace, other.workPlace);
    }

    /**
     * Возвращает хэш-код объекта служащего.
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), super.getHeight(), workPlace, salary);
    }

    /**
     * Проверяет, является ли объект служащего пустым.
     * Объект считается пустым, если имя, рост, место работы и зарплата не заданы.
     *
     * @return true, если объект пустой, иначе false
     */
    @Override
    public boolean isEmpty() {
        return super.getName() == null && super.getHeight() == 0 && workPlace == null && salary == 0;
    }
}