package capaDatosTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.*;
public class ChoferTemporarioTest {
	ChoferTemporario chofer;
	
	@Before
	public void setUp() {
		ChoferTemporario.setSueldoBasico(1000);
		chofer = new ChoferTemporario("12345678","John Smith");
	}
	
	@After
	public void tearDown() {
		chofer = null;
	}
	
	@Test
	public void ChoferTemporario_Clase1() {
		
		assertEquals("El DNI deberia ser 12345678",chofer.getDni(),"12345678");
		assertEquals("El nombre deberia ser John Smith",chofer.getNombre(),"John Smith");
	}
	
	@Test
	public void getSueldoBruto_Clase1() {
		if(chofer.getSueldoBruto() != 1000)
			fail("El sueldo bruto deberia ser 1000");
	}
}
