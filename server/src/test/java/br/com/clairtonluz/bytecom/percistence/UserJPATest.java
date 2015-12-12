package br.com.clairtonluz.bytecom.percistence;

import javax.persistence.EntityManager;

import br.com.clairtonluz.bytecom.util.CreateEntityManager;
import br.com.clairtonluz.bytecom.model.jpa.administrador.UserJPA;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserJPATest {
	private EntityManager em;
	private UserJPA userJPA;

	@Before
	public void setUp() {
		em = CreateEntityManager.INSTANCE.getEntityManager();
		userJPA = new UserJPA();
		userJPA.setEntityManager(em);
	}
	
	@Test
	public void deveriaVerificarSeEmailExiste(){
//		Assert.assertFalse(userJPA.emailExist("usernamedeteste"));
	}

	@After
	public void closeUp(){
		em.close();
	}
}