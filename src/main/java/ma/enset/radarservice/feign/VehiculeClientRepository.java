package ma.enset.radarservice.feign;

import ma.enset.radarservice.model.Vehicule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "IMMATRICULATION-SERVICE")
public interface VehiculeClientRepository {
    @GetMapping("/vehicules")
    List<Vehicule> getVehicules();

    @GetMapping("/vehicule")
    Vehicule getVehiculeByMatricule(@RequestParam String mat);
}
