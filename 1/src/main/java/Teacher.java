import java.util.Objects;

/**
 * Класс, представляющий учителя.
 * Наследует свойства класса PERSON и добавляет информацию о квалификации и количестве классов.
 */
public class Teacher extends Person {
    private String qualification; // квалификация учителя
    private int classCount;    // количество классов учителя

    /**
     * Конструктор по умолчанию. Создает пустой объект учителя.
     */
    public Teacher() {
    }

    /**
     * Конструктор с параметрами. Создает объект учителя с указанными именем, ростом, квалификацией и количеством классов.
     *
     * @param name          имя учителя
     * @param height        рост учителя
     * @param qualification квалификация учителя
     * @param classCount  количество классов
     */
    public Teacher(String name, double height, String qualification, int classCount) {
        super(name, height);
        this.qualification = qualification;
        this.classCount = classCount;
    }

    /**
     * Возвращает квалификацию учителя.
     *
     * @return квалификация
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * Устанавливает квалификацию учителя.
     *
     * @param qualification квалификация
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     * Возвращает количество классов, обучаемых учителем.
     *
     * @return количество классов
     */
    public int getClassCount() {
        return classCount;
    }

    /**
     * Устанавливает количество классов, обучаемых учителем.
     *
     * @param classCount количество классов
     */
    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }

    /**
     * Возвращает строковое представление объекта учителя.
     *
     * @return строка в формате: "Имя - [имя], рост - [рост], направление работ - [квалификация], количество классов - [количество классов]"
     */
    @Override
    public String toString() {
        return "Имя - " + (super.getName() != null ? super.getName() : "не указано") +
                ", рост - " + (super.getHeight() != 0 ? super.getHeight() : "не указан") +
                ", квалификация - " + (qualification != null ? qualification : "не указано") +
                ", количество классов - " + (classCount != 0 ? classCount : "не указано");
    }

    /**
     * Сравнивает текущий объект учителя с другим объектом на равенство.
     *
     * @param otherObject объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object otherObject) {
        if (!super.equals(otherObject)) return false;
        Teacher other = (Teacher) otherObject;
        return classCount == other.classCount && Objects.equals(qualification, other.qualification);
    }

    /**
     * Возвращает хэш-код объекта учителя.
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), super.getHeight(), qualification, classCount);
    }

    /**
     * Проверяет, является ли объект учителя пустым.
     * Объект считается пустым, если имя, рост, квалификация и количество классов не заданы.
     *
     * @return true, если объект пустой, иначе false
     */
    @Override
    public boolean isEmpty() {
        return super.getName() == null && super.getHeight() == 0 && qualification == null && classCount == 0;
    }
}