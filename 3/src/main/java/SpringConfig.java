import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {

    @Bean
    public RockMusic rockMusic() {
        return new RockMusic("Queen");
    }

    @Bean
    public ClassicalMusic classicalMusic() {
        return ClassicalMusic.getClassicalMusic("П. И. Чайковский");
    }
    @Bean
    public MusicPlayer musicPlayer(
            @Value("${musicPlayer.volume}") int volume,
            @Value("${musicPlayer.model}") String model) {
        
        MusicPlayer player = new MusicPlayer(rockMusic());
        player.setVolume(volume);
        player.setModel(model);
        return player;
    }
}