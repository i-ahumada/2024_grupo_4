package capaDatosTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;

public class AutoTest {
		Auto auto;
	@Before
	public void setUp() {
		auto = new Auto("NFG 123",3,false);
	}
	
	@After
	public void tearDown() {
		auto = null;
	}
	
	@Test
	public void Auto_Clase1() {		
		if(!(auto instanceof Auto))
			fail("No se creo una instancia de auto");
	}
	
	@Test
	public void getPatente() {
		assertEquals("La patente deberia ser NFG 123",auto.getPatente(),"NFG 123");
	}
	@Test
	public void getCantidadPlazas() {
		assertEquals("La cantidad de plazas deberia ser 3",auto.getCantidadPlazas(),3);
	}
	@Test
	public void isMascota() {
		assertEquals("Mascota deberia ser false",false, auto.isMascota());
	}
	
	@Test
	public void getPuntajePedido_Clase1_1() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),3,false,true,3,Constantes.ZONA_SIN_ASFALTAR);
		
		int puntaje = auto.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser 120", 120, puntaje);
	}
	
	@Test
	public void getPuntajePedido_Clase1_2() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),3,false,false,3,Constantes.ZONA_SIN_ASFALTAR);
		
		int puntaje = auto.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser 90",90, puntaje); // el calculo toma como si pidiese baul
	}
	
	@Test
	public void getPuntajePedido_Clase2_1() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),4,false,true,3,Constantes.ZONA_SIN_ASFALTAR);
		
		Integer puntaje = auto.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser null", puntaje,null);
	}
	
	@Test
	public void getPuntajePedido_Clase2_2() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),3,true,true,3,Constantes.ZONA_SIN_ASFALTAR);
		
		Integer puntaje = auto.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser null", puntaje,null);
	}
}
