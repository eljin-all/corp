/**
 * Класс классической музыки.
 * Содержит уникальное поле composer (композитор),
 * внедряемое через конструктор.
 */
public class ClassicalMusic implements Music {
    private String composer;

    public ClassicalMusic(String composer) {
        this.composer = composer;
    }

    @Override
    public String getSong() {
        return "Симфония композитора: " + composer;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }
}