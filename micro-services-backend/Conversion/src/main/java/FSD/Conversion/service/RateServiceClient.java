package FSD.Conversion.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import FSD.Conversion.config.FeignConfig;

@FeignClient(name = "rateservice", configuration = FeignConfig.class)
public interface RateServiceClient {

    @GetMapping("/rate/{from}/{to}")
    double getRate(@PathVariable("from") String from, @PathVariable("to") String to);

}
