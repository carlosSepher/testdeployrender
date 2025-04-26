package cl.sepher.test.controller;

import cl.sepher.test.request.LoginRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        // Aquí ignoramos la validación, siempre respondemos un token hardcodeado
        Map<String, String> response = new HashMap<>();
        response.put("token", "fake-jwt-token-12345");
        return response;
    }
    @GetMapping("/protected")
    public String protectedResource(@RequestHeader("Authorization-User") String userToken) {
        // Validamos que el token de usuario no esté vacío
        if (userToken == null || userToken.isBlank()) {
            return("Sesion no iniciada");
        }

        // En este ejemplo no validamos el contenido, solo que exista
        return "Hola usuario, tu sesion esta iniciada con el token: " + userToken;
    }
}