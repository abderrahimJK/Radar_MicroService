package ma.enset.radarservice.repository;

import ma.enset.radarservice.entities.Radar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//@RepositoryRestResource
public interface RadarRepository extends JpaRepository<Radar, Long> {
}
