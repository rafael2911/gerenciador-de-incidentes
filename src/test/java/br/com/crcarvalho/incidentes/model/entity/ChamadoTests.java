package br.com.crcarvalho.incidentes.model.entity;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.crcarvalho.incidentes.exception.UsuarioInvalidoException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChamadoTests {
	
	@Test
	public void chamadoSoPodeSerCriadoPorUsuarioComPerfilUser() {
		Role user = new Role("USER");
		
		Usuario usuario = new Usuario();
		usuario.setEmail("rafael@rafael.com");
		usuario.setNome("Rafael");
		usuario.setSenha("abc");
		usuario.setStatus(StatusUsuario.ATIVO);
		usuario.setRoles(Arrays.asList(user));
		
		Chamado chamado = new Chamado();
		chamado.setTitulo("Chamado de teste");
		chamado.setDescricao("Testando inclusao de chamado");
		chamado.setRequerente(usuario);
		chamado.setOrigem(new Origem("Telefone"));
		chamado.setCategoria(new Categoria("Office"));
		chamado.setSla(new Sla("4_DIAS", 4));
		chamado.setStatus(StatusChamado.ABERTO);
		
		assertEquals(true, chamado.getRequerente().getRoles().contains(new Role("USER")));
		
	}
	
	@Test(expected=UsuarioInvalidoException.class)
	public void chamadoNaoPodeSerCriadoPorUsuarioSemPerfilUser() {
		Role admin = new Role("ADMIN");
		
		Usuario usuario = new Usuario();
		usuario.setEmail("rafael@rafael.com");
		usuario.setNome("Rafael");
		usuario.setSenha("abc");
		usuario.setStatus(StatusUsuario.ATIVO);
		usuario.setRoles(Arrays.asList(admin));
		
		Chamado chamado = new Chamado();
		chamado.setTitulo("Chamado de teste");
		chamado.setDescricao("Testando inclusao de chamado");
		chamado.setRequerente(usuario);
		chamado.setOrigem(new Origem("Telefone"));
		chamado.setCategoria(new Categoria("Office"));
		chamado.setSla(new Sla("4_DIAS", 4));
		
		assertEquals(false, chamado.getRequerente().getRoles().contains(new Role("USER")));
		
	}
	
}
