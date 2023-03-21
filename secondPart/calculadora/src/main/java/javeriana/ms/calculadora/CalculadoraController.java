package javeriana.ms.calculadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;





@RestController
public class CalculadoraController {


    @Autowired
    RestTemplate restTemplate;


    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        RestTemplate restTemplate1 = new RestTemplate();
        return restTemplate1;
    }

    @GetMapping("/calculadora/suma")
    public String calculadoraSuma(@RequestParam int a, @RequestParam int b, @RequestParam String user) throws IOException {
        String response = restTemplate.getForObject("http://sumador/suma?a={a}&b={b}", String.class, a, b);
        return response;
    }

    @GetMapping("/calculadora/resta")
    public String calculadoraResta(@RequestParam int a, @RequestParam int b, @RequestParam String user)  {
        String response = restTemplate.getForObject("http://restador/resta?a={a}&b={b}", String.class, a, b);

        return response;
    }

    @GetMapping("/calculadora/multip")
    public String calculadoraMultip(@RequestParam int a, @RequestParam int b, @RequestParam String user)  {
        String response = restTemplate.getForObject("http://multiplicador/multip?a={a}&b={b}", String.class, a, b);

        return response;
    }

    @GetMapping("/calculadora/div")
    public String calculadoraDiv(@RequestParam int a, @RequestParam int b, @RequestParam String user) {
        String response = restTemplate.getForObject("http://dividor/div?a={a}&b={b}", String.class, a, b);

        return response;
    }





}
