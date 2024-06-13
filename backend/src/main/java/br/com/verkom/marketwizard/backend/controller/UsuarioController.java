//package br.com.verkom.marketwizard.backend.controller;
//
//
//import br.com.verkom.marketwizard.backend.model.Usuario;
//import br.com.verkom.marketwizard.backend.service.UsuarioService;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/usuario")
//@CrossOrigin
//public class UsuarioController {
//
//    private final UsuarioService usuarioService;
//
//    public UsuarioController(UsuarioService usuarioService) {
//        this.usuarioService = usuarioService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Usuario>> getAllUsuarios() {
//        List<Usuario> usuarios =  usuarioService.getAllUsuarios();
//        return ResponseEntity.ok().body(usuarios);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
//        Usuario usuario = usuarioService.getUsuarioById(id);
//        return ResponseEntity.ok().body(usuario);
//    }
//
//    @PostMapping
//    public ResponseEntity<Usuario> createUsuario(@RequestBody @Valid Usuario usuario) {
//        Usuario createdUsuario = usuarioService.createUsuario(usuario);
//        return ResponseEntity.ok().body(createdUsuario);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuarioDetails) {
//        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuarioDetails);
//        return ResponseEntity.ok().body(updatedUsuario);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
//        usuarioService.deleteUsuario(id);
//        return ResponseEntity.ok().build();
//    }
//
//}
