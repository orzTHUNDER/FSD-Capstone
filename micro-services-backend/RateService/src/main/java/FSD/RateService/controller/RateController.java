package FSD.RateService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {

    private static final Map<String, Double> rates = new HashMap<>();

    static {
        rates.put("USD_EUR", 0.92);
        rates.put("EUR_USD", 1.08);
        rates.put("USD_INR", 83.24);
        rates.put("INR_USD", 0.012);
        rates.put("EUR_INR", 90.25);
        rates.put("INR_EUR", 0.011);
        rates.put("USD_USD", 1.00);
        rates.put("EUR_EUR", 1.00);
        rates.put("INR_INR", 1.00);
    }

    @GetMapping("/rate/{from}/{to}")
    public ResponseEntity<String> getRate(@PathVariable String from, @PathVariable String to) {
        String key = from + "_" + to;
        if (!rates.containsKey(key)) {
            return ResponseEntity.badRequest().body("Invalid Currency!");
        }
        double rate = rates.get(key);
        return ResponseEntity.ok(String.valueOf(rate));
    }
}
