//package br.com.verkom.marketwizard.backend.service;
//
//import br.com.verkom.marketwizard.backend.exception.RecursoNaoEncontradoException;
//import br.com.verkom.marketwizard.backend.exception.ViolacaoChaveEstrengeiraException;
//import br.com.verkom.marketwizard.backend.model.Categoria;
//import br.com.verkom.marketwizard.backend.model.Usuario;
//import br.com.verkom.marketwizard.backend.repository.CategoriaRepository;
//import br.com.verkom.marketwizard.backend.repository.ProdutoRepository;
//import br.com.verkom.marketwizard.backend.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UsuarioService {
//
//    private final UsuarioRepository usuarioRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
//        this.usuarioRepository = usuarioRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public List<Usuario> getAllUsuarios() {
//        return usuarioRepository.findAll();
//    }
//
//    public Usuario getUsuarioById(Long id) {
//        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
//        if (usuarioOptional.isEmpty()) {
//            throw new RecursoNaoEncontradoException("Usuario de id: " + id + " não encontrado.");
//        }
//        return usuarioOptional.get();
//    }
//
//    public Usuario createUsuario(Usuario usuario) {
//        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
//        return usuarioRepository.save(usuario);
//    }
//
//    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
//        Usuario usuario = getUsuarioById(id);
//        usuario.setNome(usuarioDetails.getNome());
//        usuario.setLogin(usuarioDetails.getLogin());
//        usuario.setEmail(usuarioDetails.getEmail());
//        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
//        return usuarioRepository.save(usuario);
//    }
//
//    public void deleteUsuario(Long id) {
//        Usuario usuario = getUsuarioById(id);
//        usuarioRepository.delete(usuario);
//    }
//
//}
