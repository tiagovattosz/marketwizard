//package br.com.verkom.marketwizard.backend.service;
//
//import br.com.verkom.marketwizard.backend.model.Usuario;
//import br.com.verkom.marketwizard.backend.repository.UsuarioRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UsuarioRepository usuarioRepository;
//
//    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
//        this.usuarioRepository = usuarioRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
//        if(usuario.isEmpty()){
//            throw new UsernameNotFoundException("Login não encontrado");
//        }
//
//        return UserDetailsImpl.build(usuario.get());
//    }
//}
