//package br.com.verkom.marketwizard.backend.controller;
//
//import br.com.verkom.marketwizard.backend.dto.AuthenticationDto;
//import br.com.verkom.marketwizard.backend.service.AuthService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin
//public class AuthController {
//
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping(value = "/login")
//    public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto){
//        return ResponseEntity.ok(authService.login(authenticationDto)) ;
//    }
//
//}
