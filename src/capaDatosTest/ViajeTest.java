package capaDatosTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import util.Constantes;

public class ViajeTest {
	public Viaje viaje;
	public Pedido pedido;
	public Chofer chofer;
	public Vehiculo vehiculo;
	
	
	@Before
	public void setUp() {
		Cliente cliente = new Cliente("ramonDiaz","12345678","Ramon Diaz");
		pedido = new Pedido(cliente,4,true,true,10,Constantes.ZONA_STANDARD);
		chofer = new ChoferTemporario("22312151","Joaquin Valiente");
		vehiculo = new Auto("ASD 333",4,true);
		
		viaje = new Viaje(pedido,chofer,vehiculo);
		Viaje.setValorBase(1500);
	}
	@After
	public void tearDown() {
		viaje = null;
		pedido = null;
		chofer = null;
		vehiculo = null;
	}
	
	@Test
	public void Viaje_Clase1() {
		assertEquals("La instancia de pedido no coincide", pedido, viaje.getPedido());
		assertEquals("La instancia de chofer no coincide",chofer,viaje.getChofer());
		assertEquals("La instancia de vehiculo no coincide",vehiculo,viaje.getVehiculo());
		assertEquals("La calificacion deberia ser 0",0,viaje.getCalificacion());
		assertEquals("El atributo finalizado deberia ser false",false,viaje.isFinalizado());
	}
	@Test
	public void getPedido() {
		assertEquals("La instancia de pedido no coincide",pedido,viaje.getPedido());
	}
	@Test
	public void getChofer() {
		assertEquals("La instancia de chofer no coincide",chofer,viaje.getChofer());
	}
	@Test
	public void getVehiculo() {
		assertEquals("La instancia de vehiculo no coincide",vehiculo,viaje.getVehiculo());
	}
	@Test
	public void getCalificacion() {
		assertEquals("La calificacion deberia ser 0",0,viaje.getCalificacion());
	}
	@Test
	public void isFinalizado() {
		assertEquals("El atributo finalizado deberia ser false",false,viaje.isFinalizado());
	}
	@Test
	public void getValorBase(){
		assertEquals("El valor del viaje deberia ser 1500", 1500, viaje.getValorBase(),0.1);
	}
	@Test
	public void setValorBase() {
		Viaje.setValorBase(3000);
		assertEquals("El valor base deberia ser 3000", 3000, viaje.getValorBase(),0.1);
	}
	
	@Test
	public void finalizarViaje_Clase1() {
		viaje.finalizarViaje(4);
		assertEquals("El atributo finalizado deberia ser true",true,viaje.isFinalizado());	
		assertEquals("El atributo calificacion deberia ser 4",4,viaje.getCalificacion());
	}
	
	@Test
	public void getValor_STANDARD() {
		Cliente cliente = new Cliente("ramonDiaz","12345678","Ramon Diaz");
		Pedido pedido = new Pedido(cliente,4,false,false,10,Constantes.ZONA_STANDARD);
		Chofer chofer = new ChoferTemporario("22312151","Joaquin Valiente");
		Vehiculo vehiculo = new Auto("ASD 333",4,true);
		
		Viaje viaje = new Viaje(pedido,chofer,vehiculo);
		
		assertEquals("El valor del viaje deberia ser 3600", 3600, viaje.getValor(),0.1);
	}
	
	@Test
	public void getValor_PELIGROSA() {
		Cliente cliente = new Cliente("ramonDiaz","12345678","Ramon Diaz");
		Pedido pedido = new Pedido(cliente,4,false,false,10,Constantes.ZONA_PELIGROSA);
		Chofer chofer = new ChoferTemporario("22312151","Joaquin Valiente");
		Vehiculo vehiculo = new Auto("ASD 333",4,true);
		
		Viaje viaje = new Viaje(pedido,chofer,vehiculo);
		
		assertEquals("El valor del viaje deberia ser 5100", 5100, viaje.getValor(),0.1);
	}
	
	@Test
	public void getValor_SIN_ASFALTAR() {
		Cliente cliente = new Cliente("ramonDiaz","12345678","Ramon Diaz");
		Pedido pedido = new Pedido(cliente,4,false,false,10,Constantes.ZONA_SIN_ASFALTAR);
		Chofer chofer = new ChoferTemporario("22312151","Joaquin Valiente");
		Vehiculo vehiculo = new Auto("ASD 333",4,true);
		
		Viaje viaje = new Viaje(pedido,chofer,vehiculo);
		
		assertEquals("El valor del viaje deberia ser 4950", 4950, viaje.getValor(),0.1);
	}
	
	@Test
	public void getValor_CON_MASCOTA() {
		Cliente cliente = new Cliente("ramonDiaz","12345678","Ramon Diaz");
		Pedido pedido = new Pedido(cliente,4,true,false,10,Constantes.ZONA_STANDARD);
		Chofer chofer = new ChoferTemporario("22312151","Joaquin Valiente");
		Vehiculo vehiculo = new Auto("ASD 333",4,true);
		
		Viaje viaje = new Viaje(pedido,chofer,vehiculo);

		assertEquals("El valor del viaje deberia ser 8700", 8700, viaje.getValor(),0.1);
	}
	
	@Test
	public void getValor_CON_BAUL() {
		Cliente cliente = new Cliente("ramonDiaz","12345678","Ramon Diaz");
		Pedido pedido = new Pedido(cliente,4,false,true,10,Constantes.ZONA_STANDARD);
		Chofer chofer = new ChoferTemporario("22312151","Joaquin Valiente");
		Vehiculo vehiculo = new Auto("ASD 333",4,true);
		
		Viaje viaje = new Viaje(pedido,chofer,vehiculo);
		
		assertEquals("El valor del viaje deberia ser 4950", 4950, viaje.getValor(),0.1);
	}
	
}
