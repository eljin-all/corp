import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;



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

    @PostConstruct
    public void init() {
        System.out.println(">> [Init] Инициализация плеера: включение питания и калибровка системы...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(">> [Destroy] Уничтожение бина: освобождение системных ресурсов и выключение питания.");
    }

    public void playMusic() {
        System.out.println("Модель плеера: " + model);
        System.out.println("Уровень громкости: " + volume + "%");
        System.out.println("Сейчас играет: " + music.getSong());
    }
}