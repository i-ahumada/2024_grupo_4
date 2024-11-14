package capaDatosTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import util.Constantes;

public class MotoTest {
	Moto moto;
	
	@Before
	public void setUp() {
		moto = new Moto("JWL 456");
	}
	
	@After
	public void tearDown() {
		moto = null;
	}
	
	@Test
	public void getPatente() {
		assertEquals("La patente deberia ser JWL 456",moto.getPatente(),"JWL 456");
	}
	@Test
	public void getCantidadPlazas() {
		assertEquals("La cantidad de plazas deberia ser 1",moto.getCantidadPlazas(),1);
	}
	@Test
	public void isMascota() {
		assertEquals("Mascota deberia ser false",moto.isMascota(),false);
	}
	
	@Test
	public void getPuntajePedido_Clase1_1() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),1,false,false,3,Constantes.ZONA_SIN_ASFALTAR);
		
		int puntaje = moto.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser 1000", puntaje,1000);
	}
	
	@Test
	public void getPuntajePedido_Clase2_1() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),1,false,true,3,Constantes.ZONA_SIN_ASFALTAR);
		
		Integer puntaje = moto.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser null", puntaje,null); 
	}
	
	@Test
	public void getPuntajePedido_Clase2_2() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),2,false,false,3,Constantes.ZONA_SIN_ASFALTAR);
		
		Integer puntaje = moto.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser null", puntaje,null);
	}
	
	@Test
	public void getPuntajePedido_Clase2_3() {
		Pedido pedido = new Pedido(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"),3,true,false,3,Constantes.ZONA_SIN_ASFALTAR);
		
		Integer puntaje = moto.getPuntajePedido(pedido);
		assertEquals("El puntaje debe ser null", puntaje,null);
	}
}
