package FSD.Conversion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import FSD.Conversion.service.RateServiceClient;

@RestController
public class ConversionController {

    @Autowired
    private RateServiceClient rateServiceClient;

    @GetMapping("/convert/{from}/{to}/{amount}")
    public ResponseEntity<?> convert(@PathVariable String from, @PathVariable String to, @PathVariable int amount) {

        if (amount < 0) {
            return ResponseEntity.badRequest().body("Amount cannot be negative!");
        }

        double rate = rateServiceClient.getRate(from, to);
        return ResponseEntity.ok().body(amount * rate);
    }

}
