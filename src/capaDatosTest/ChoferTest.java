package capaDatosTest;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
public class ChoferTest {
	Chofer chofer;
	
	@Before
	public void setUp() {
		Chofer.setSueldoBasico(1000);
		Chofer chofer = new ChoferTemporario("12345678","Esteben Echeverria");
	}
	
	@After
	public void tearDown() {
		chofer = null;
	}
	
	@Test
	public void getSueldoBasico() { // me quedo extraño
		if(Chofer.getSueldoBasico()!=1000)
			fail("El sueldo basico deberia ser 1000");
	}
	
	@Test
	public void setSueldoBasico_Clase1() {
		Chofer.setSueldoBasico(1000);
		//assertEquals("El sueldo basico deberia ser 1000",chofer.getSueldoBasico(),1000);
		if(Chofer.getSueldoBasico()!=1000)
			fail("El sueldo basico deberia ser 1000");
	}
	@Test
	public void getSueldoNeto_Clase1() {
		Chofer chofer = new ChoferTemporario("12345678","Esteben Echeverria");
		if(chofer.getSueldoNeto()!=860)
			fail("El sueldo neto deberia ser 860");
	}
}
