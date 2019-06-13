package br.com.crcarvalho.incidentes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.crcarvalho.incidentes.model.entity.Usuario;
import br.com.crcarvalho.incidentes.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return Optional.ofNullable(usuarioRepository.findOne(username)).orElseThrow(()-> new UsernameNotFoundException("Usuário ou Senha incorretos."));
	}

	public List<Usuario> findAll() {
		
		return this.usuarioRepository.findAll();
	}
	
	public Usuario save(Usuario usuario) {
		
		return this.usuarioRepository.save(usuario);
	}
	
	public Usuario find(String email) {
		
		return this.usuarioRepository.findOne(email);

	}

}
