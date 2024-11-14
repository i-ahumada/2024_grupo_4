package  capaDatosTest;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.*;
public class ClienteTest {
	Cliente cliente;
	
	@Before
	public void setUp() {
		cliente = new Cliente("lionel10","12345678","Lionel Cuccitini"); 
	}
	
	@After
	public void tearDown() {
		cliente = null;
	}
	
	@Test
	public void Cliente_Clase1() {
		
		assertEquals("El nombre de usuario deberia ser lionel10", cliente.getNombreUsuario(),"lionel10");
		assertEquals("El nombre de real deberia ser Lionel Cuccitini", cliente.getNombreReal(),"Lionel Cuccitini");
		assertEquals("El password deberia ser 12345678", cliente.getPass(),"12345678");

	}
	
	@Test
	public void getNombreUsuario() {
		assertEquals("El nombre de usuario deberia ser lionel10", cliente.getNombreUsuario(),"lionel10");
	}
	
	@Test
	public void getNombreReal() {
		assertEquals("El nombre de real deberia ser Lionel Cuccitini", cliente.getNombreReal(),"Lionel Cuccitini");
	}
	
	@Test
	public void getPass() {
		assertEquals("El password deberia ser 12345678", cliente.getPass(),"12345678");
	}
}
