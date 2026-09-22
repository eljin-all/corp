/**
 * Класс Музыкального плеера (сущность варианта 1).
 * Внедрение зависимости Music осуществляется через конструктор.
 * Внедрение простых полей (volume, model) осуществляется через setter из properties-файла.
 */
public class MusicPlayer {
    private Music music;
    private int volume;
    private String model;

    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void playMusic() {
        System.out.println("Модель плеера: " + model);
        System.out.println("Уровень громкости: " + volume + "%");
        System.out.println("Сейчас играет: " + music.getSong());
    }
}