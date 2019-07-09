package br.com.crcarvalho.incidentes.model.entity;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChamadoTests {
	
	@Test
	public void chamadoSoPodeSerCriadoPorUsuarioComPerfilUser() {
		Role user = new Role("ROLE_USER");
		
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
		chamado.setOrigem(new Origem(1L, "Telefone"));
		chamado.setCategoria(new Categoria(1L, "Office"));
		chamado.setSla(new Sla("4_DIAS", 4));
		
		assertEquals(true, chamado.getRequerente().possuiRole("ROLE_USER"));
		
	}
	
	@Test(expected=AccessDeniedException.class)
	public void chamadoNaoPodeSerCriadoPorUsuarioSemPerfilUser() {
		Role admin = new Role("ROLE_ADMIN");
		
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
		chamado.setOrigem(new Origem(1L, "Telefone"));
		chamado.setCategoria(new Categoria(1L, "Office"));
		chamado.setSla(new Sla("4_DIAS", 4));
		
		assertEquals(false, chamado.getRequerente().possuiRole("ROLE_USER"));
		
	}
	
}
