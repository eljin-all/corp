
import java.util.Objects;

/**
 * Класс, представляющий студента.
 * Наследует свойства класса PERSON и добавляет информацию о группе здоровья и количестве учебных часов в день.
 */
public class Student extends Person {
    private String healthGroup; // группа здоровья студента
    private int studyHours; // количество учебных часов

    /**
     * Конструктор по умолчанию. Создает пустой объект студента.
     */
    public Student() {
    }

    /**
     * Конструктор с параметрами. Создает объект студента с указанными именем, ростом, группой здоровья и количеством учебныхчасов в день.
     *
     * @param name        имя студента
     * @param height      рост студента
     * @param healthGroup группа здоровья студента
     * @param studyHours    количество учебных часов в день
     */
    public Student(String name, double height, String healthGroup, int studyHours) {
        super(name, height);
        this.healthGroup = healthGroup;
        this.studyHours = studyHours;
    }

    /**
     * Возвращает группу здоровья студеноа.
     *
     * @return группа здоровья
     */
    public String getHealthGroup() {
        return healthGroup;
    }

    /**
     * Устанавливает группу здоровья студента.
     *
     * @param healthGroup группа здоровья
     */
    public void setHealthGroup(String healthGroup) {
        this.healthGroup = healthGroup;
    }

    /**
     * Возвращает количество учебных часов в день.
     *
     * @return количество учебных часов в день
     */
    public int getStudyHours() {
        return studyHours;
    }

    /**
     * Устанавливает количество учебных часов в день.
     *
     * @param studyHours учебных рабочих часов в день
     */
    public void setStudyHours(int studyHours) {
        this.studyHours = studyHours;
    }

    /**
     * Возвращает строковое представление объекта студента.
     *
     * @return строка в формате: "Имя - [имя], рост - [рост], группа здоровья - [группа здоровья], рабочих часов в день - [количество часов]"
     */
    @Override
    public String toString() {
        return "Имя - " + (super.getName() != null ? super.getName() : "не указано") +
                ", рост - " + (super.getHeight() != 0 ? super.getHeight() : "не указан") +
                ", группа здоровья - " + (healthGroup != null ? healthGroup : "не указана") +
                ", учебных часов в день - " + (studyHours != 0 ? studyHours : "не указано");
    }

    /**
     * Сравнивает текущий объект студента с другим объектом на равенство.
     *
     * @param otherObject объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object otherObject) {
        if (!super.equals(otherObject)) return false;
        Student other = (Student) otherObject;
        return studyHours == other.studyHours && Objects.equals(healthGroup, other.healthGroup);
    }

    /**
     * Возвращает хэш-код объекта рабочего.
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), super.getHeight(), healthGroup, studyHours);
    }

    /**
     * Проверяет, является ли объект рабочего пустым.
     * Объект считается пустым, если имя, рост, группа здоровья и количество рабочих часов не заданы.
     *
     * @return true, если объект пустой, иначе false
     */
    @Override
    public boolean isEmpty() {
        return super.getName() == null && super.getHeight() == 0 && healthGroup == null && studyHours == 0;
    }
}