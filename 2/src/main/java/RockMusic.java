/**
 * Класс рок-музыки.
 * Содержит уникальное поле bandName (название группы),
 * внедряемое через конструктор.
 */
public class RockMusic implements Music {
    private String bandName;

    public RockMusic(String bandName) {
        this.bandName = bandName;
    }

    @Override
    public String getSong() {
        return "Рок-трек группы " + bandName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}