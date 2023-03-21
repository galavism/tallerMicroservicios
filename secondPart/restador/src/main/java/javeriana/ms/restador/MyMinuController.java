package javeriana.ms.restador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyMinuController {

    @Autowired
    Environment environment;

    @GetMapping("/resta")
    public String minus(@RequestParam int a, @RequestParam int b){
        String port= environment.getProperty("local.server.port");
        int result = a-b;
        String response = "Resultado: " + result + " -> Microservicio suma corriendo en el puerto: "+ port ;
        return response;
    }
    
}
