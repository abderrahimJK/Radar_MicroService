package ma.enset.radarservice;

import ma.enset.radarservice.entities.Radar;
import ma.enset.radarservice.repository.RadarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RadarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RadarServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RadarRepository radarRepository){
        return args -> {
            for (int i = 0; i < 4; i++) {
                radarRepository.save(
                        new Radar().builder()
                                .id(null)
                                .vitesseMax(120)
                                .longitude(Math.random()*100)
                                .latitude(Math.random()*100)
                                .build()
                );
            }
        };
    }
}
