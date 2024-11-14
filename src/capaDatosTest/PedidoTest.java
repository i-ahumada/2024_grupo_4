package capaDatosTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;

public class PedidoTest {
	Pedido pedido;
	
	@Before
	public void setUp() {
		Cliente cliente = new Cliente("ramonDiaz","12345678","Ramon Diaz");
		pedido = new Pedido(cliente,4,true,true,10,Constantes.ZONA_STANDARD);
	}
	@After
	public void tearDown() {
		pedido = null;
	}
	
	@Test
	public void Pedido_Clase1() {
		assertEquals("El cliente deberia ser Ramon Diaz","Ramon Diaz",pedido.getCliente().getNombreReal());
		assertEquals("La cantidad de pasajeros deberia ser 4",4,pedido.getCantidadPasajeros());
		assertEquals("El atributo mascota deberia ser true",true,pedido.isMascota());
		assertEquals("El atributo baul deberia ser true",true,pedido.isBaul());
		assertEquals("Los KM deberian ser 10",10,pedido.getKm());
		assertEquals("La zona deberia ser Zona Estandar",Constantes.ZONA_STANDARD,pedido.getZona());
	}
	
	@Test
	public void getCliente() {
		assertEquals("El cliente deberia ser Ramon Diaz","Ramon Diaz",pedido.getCliente().getNombreReal());
	}
	
	@Test
	public void getCantidadPasajeros() {
		assertEquals("La cantidad de pasajeros deberia ser 4",4,pedido.getCantidadPasajeros());
	}
	
	@Test
	public void isMascota() {
		assertEquals("El atributo mascota deberia ser true",true,pedido.isMascota());
	}
	
	@Test
	public void isBaul() {
		assertEquals("El atributo baul deberia ser true",true,pedido.isBaul());
	}
	
	@Test
	public void getKm() {
		assertEquals("Los KM deberian ser 10",10,pedido.getKm());
	}
	
	@Test
	public void getZona() {
		assertEquals("La zona deberia ser Zona Estandar",Constantes.ZONA_STANDARD,pedido.getZona());
	}
}
