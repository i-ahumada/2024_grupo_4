package capaDatosTest;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import util.Constantes;

public class CombiTest {
	Combi combi;
	
	@Before
	public void setUp() {
		combi = new Combi("AABBCC123",7,false);
	}
	@After
	public void tearDown() {
		combi = null;
	}
	
	@Test
	public void Combi_Clase1() {
		assertEquals("La patente deberia ser AABBCC123",combi.getPatente(),"AABBCC123");
		assertEquals("La cantidad de plazas deberia ser 7",combi.getCantidadPlazas(),7);
		assertEquals("Mascota deberia ser false",combi.isMascota(),false);
	}
	
	@Test
	public void getPatente() {
		assertEquals("La patente deberia ser AABBCC123",combi.getPatente(),"AABBCC123");
	}
	@Test
	public void getCantidadPlazas() {
		assertEquals("La cantidad de plazas deberia ser 7",combi.getCantidadPlazas(),7);
	}
	@Test
	public void isMascota() {
		assertEquals("Mascota deberia ser false",combi.isMascota(),false);
	}
	
	@Test
	public void getPuntajePedido_Clase1_1() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),1,false,true,3,Constantes.ZONA_SIN_ASFALTAR);
		
		int puntaje = combi.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser 130", puntaje,130);
	}
	
	@Test
	public void getPuntajePedido_Clase1_2() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),3,false,false,3,Constantes.ZONA_SIN_ASFALTAR);
		
		int puntaje = combi.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser 30", puntaje,30);
	}
	
	@Test
	public void getPuntajePedido_Clase2_1() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),9,false,true,3,Constantes.ZONA_SIN_ASFALTAR);
		
		Integer puntaje = combi.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser null", puntaje,null);
	}
	
	@Test
	public void getPuntajePedido_Clase2_2() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),9,true,true,3,Constantes.ZONA_SIN_ASFALTAR);
		
		Integer puntaje = combi.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser null", puntaje,null);
	}
}
