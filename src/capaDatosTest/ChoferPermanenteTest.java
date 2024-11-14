package capaDatosTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import modeloDatos.*;
public class ChoferPermanenteTest {
	ChoferPermanente chofer;
	
	@Before
	public void setUp() {
		ChoferPermanente.setSueldoBasico(1000);
		chofer = new ChoferPermanente("23249123","Martin Salamanca", 1990, 3);
		ChoferPermanente.setSueldoBasico(1000);
	}
	
	@After
	public void tearDown() {
		chofer = null;
	}
	
	@Test
	public void ChoferPermanente_Clase1() {
		assertEquals("El DNI deberia ser 23249123",chofer.getDni(),"23249123");
		assertEquals("El nombre deberia ser Martin Salamanca",chofer.getNombre(),"Martin Salamanca");
		assertEquals("El anio de ingreso deberia ser 1990",chofer.getAnioIngreso(),1990);
		assertEquals("La cantidad de hijos deberia ser 3",chofer.getCantidadHijos(),3);
	}
	
	@Test
	public void getDni() {
		assertEquals("El DNI deberia ser 23249123",chofer.getDni(),"23249123");
	}
	@Test
	public void getNombre() {
		assertEquals("El nombre deberia ser Martin Salamanca",chofer.getNombre(),"Martin Salamanca");
	}
	@Test
	public void getAnioIngreso() {
		assertEquals("El anio de ingreso deberia ser 1990",chofer.getAnioIngreso(),1990);
	}
	@Test
	public void getCantidadHijos() {
		assertEquals("La cantidad de hijos deberia ser 3",chofer.getCantidadHijos(),3);
	}
	
	@Test
	public void getAntiguedad_Clase1() {
		assertEquals("La antiguedad deberia ser 34",chofer.getAntiguedad(),34);
	}
	
	@Test
	public void setCantidadHijos_Clase1() {
		chofer.setCantidadHijos(5);
		assertEquals("La cantidad de hijos deberia ser 5", chofer.getCantidadHijos(),5);
	}
	
	@Test
	public void getSueldoBruto_Clase1_1() {
		ChoferPermanente chofer = new ChoferPermanente("23249123","Martin Salamanca", 2020, 3);
		assertEquals("El sueldo bruto deberia ser 1410", chofer.getSueldoBruto(),1410,0.1);

	}
	
	@Test
	public void getSueldoBruto_Clase1_2() {
		assertEquals("El sueldo bruto deberia ser 2210", chofer.getSueldoBruto(),2210,0.1);
	
	}
	
}
