//package br.com.verkom.marketwizard.backend.service;
//
//import br.com.verkom.marketwizard.backend.dto.AccessDto;
//import br.com.verkom.marketwizard.backend.dto.AuthenticationDto;
//import br.com.verkom.marketwizard.backend.security.jwt.JwtUtils;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//
//    private final AuthenticationManager authenticationManager;
//
//    private final JwtUtils jwtUtils;
//
//    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtils = jwtUtils;
//    }
//
//    //Cria mecanismo de credencial para o spring
//    public AccessDto login(AuthenticationDto authenticationDto){
//       try {
//           UsernamePasswordAuthenticationToken userAuth =
//                   new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(), authenticationDto.getPassword());
//
//           //Prepara mecanismo para autenticação
//           Authentication authentication = authenticationManager.authenticate(userAuth);
//
//           //Busca usuario logado
//           UserDetailsImpl userAuthenticated = (UserDetailsImpl) authentication.getPrincipal();
//
//           String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticated);
//
//           return new AccessDto(token);
//
//       } catch (BadCredentialsException e){
//           //TODO LOGIN OU SENHA INVALIDO
//       }
//       return new AccessDto("Token Invalido");
//
//    }
//
//}
