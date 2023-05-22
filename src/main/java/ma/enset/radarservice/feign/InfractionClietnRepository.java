package ma.enset.radarservice.feign;


import ma.enset.radarservice.model.Infraction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "INFRACTION-SERVICE")
public interface InfractionClietnRepository {

    @PostMapping("/infractions")
    Infraction save(@RequestBody Infraction infraction);
}
