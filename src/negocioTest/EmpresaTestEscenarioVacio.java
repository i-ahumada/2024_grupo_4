package negocioTest;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import excepciones.ChoferNoDisponibleException;
import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.ClienteSinViajePendienteException;
import excepciones.PasswordErroneaException;
import excepciones.PedidoInexistenteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import modeloDatos.Pedido;
import modeloDatos.Usuario;

import static util.Constantes.*;

import java.util.ArrayList;
import java.util.HashMap;


public class EmpresaTestEscenarioVacio {
	private EscenarioVacio escenario = new EscenarioVacio();
	
	@Before
	public void setUp() {
		escenario.setUp();
		
	}
	
	@After
	public void tearDown() {
		escenario.tearDown();
	}
	
	@Test
	public void testAgregarChofer_Clase1() {
		try {
			escenario.empresa.agregarChofer(new ChoferTemporario("32909289","julian"));
			if(!escenario.empresa.getChoferes().containsKey("32909289"))
				fail("No se añadio a la coleccion de choferes");
		} catch (ChoferRepetidoException e) {
			fail("La coleccion de choferes esta vacia, no deberia entrar aqui");
		}
	}
	
	@Test
	public void testAgregarCliente_Clase1() { 
		try {
			escenario.empresa.agregarCliente("marki3000",  "12345678",  "Marcos Gutierrez");
			if (!escenario.empresa.getClientes().containsKey("marki3000")) {
				fail("No se añadio a la coleccion de clientes");
			}
		} catch(UsuarioYaExisteException e) {
			fail("La coleccion de clientes esta vacia, no deberia entrar aqui");
		}
	}
	
	@Test
	public void testAgregarVehiculo_Clase1() {
		try {
			escenario.empresa.agregarVehiculo(new Auto("JJ888JJ", 3, false));
			if (!escenario.empresa.getVehiculos().containsKey("JJ888JJ")) {
				fail("No se añadio a la coleccion de clientes");
			}
		} catch(VehiculoRepetidoException e) {
			fail("La coleccion de clientes esta vacia, no deberia entrar aqui");
		}
	}
	
	@Test 
	public void testAgregarPedido_Clase1() {
		Cliente cliente = new Cliente("Esteban Gutierrez", "12345678", "estaban1200");
		try {
			escenario.empresa.agregarPedido(new Pedido(cliente, 1, true, true, 10, ZONA_STANDARD));
			if (escenario.empresa.getPedidos().containsKey(cliente)) {
				fail("Se añadio a la coleccion de pedidos, pero deberia haber saltado una excepcion");
			}
		} catch(ClienteConPedidoPendienteException e) {
			fail("La coleccion de clientes esta vacia, no deberia entrar aqui");
		}catch(ClienteConViajePendienteException e) {
			fail("La coleccion de clientes esta vacia, no deberia entrar aqui");
		} catch(ClienteNoExisteException e) {
		} catch (SinVehiculoParaPedidoException e) {
			fail("La coleccion de vehiculos esta vacia, no deberia entrar aqui");
		}
	}
	
	@Test
	public void testValidarPedido_Clase1() {
		Cliente cliente = new Cliente("Esteban Gutierrez", "12345678", "estaban1200");
		boolean res = escenario.empresa.validarPedido(new Pedido(cliente,1, true, true, 10, ZONA_STANDARD));
		if (res != false) {
			fail("El resultado de validarPedido debia ser falso ya que la lista de vehiculos esta vacia");
		}

	}
	
	@Test
	public void testCrearViaje_Clase1() {
		Cliente cliente = new Cliente("Esteban Gutierrez", "12345678", "estaban1200");   
		Chofer chofer = new ChoferTemporario("32909289", "Julian");
		Vehiculo vehiculo = new Auto("JJ888JJ" , 3, false);
		
		try {
			escenario.empresa.crearViaje(new Pedido(cliente,3,false,false,3,"ZONA_SIN_ASFALTAR"), chofer, vehiculo);
			fail("Debio salir por alguna excepcion");
		} catch (PedidoInexistenteException e) {
			
		} catch (ChoferNoDisponibleException e) {
			
		} catch (VehiculoNoDisponibleException e) {
			
		} catch (VehiculoNoValidoException e) {
			
		} catch (ClienteConViajePendienteException e) {
			fail("No es posible, ya que la lista de clientes esta vacia y no hay viajes");
		}
	}
	
	@Test
	public void testGetChoferes_Clase1() {
		if (!escenario.empresa.getChoferes().isEmpty()) {
			fail("La coleccion de choferes deberia estar vacia");
		}
	}
	
	@Test
	public void testGetChoferesDesocupados_Clase1() {
		if (!escenario.empresa.getChoferesDesocupados().isEmpty()) {
			fail("La coleccion de choferes desocupados deberia estar vacia");
		}
	}
	
	@Test
	public void testGetClientes_Clase1() {
		if (!escenario.empresa.getClientes().isEmpty()) {
			fail("La coleccion de clientes deberia estar vacia");
		}
	}
	
	@Test
	public void testGetHistorialViajeChofer_Clase1() {
		Chofer chofer = new ChoferTemporario("32909289", "Julian");
		if (!escenario.empresa.getHistorialViajeChofer(chofer).isEmpty()) {
			fail("El chofer no deberia existir en la empresa por lo que el historial de viajes deberia estar vacio");
		}
	}
	

	@Test
	public void testGetHistorialViajeCliente_Clase1() {
		Cliente cliente = new Cliente("Matis Ramirez", "12345678", "matiRAMI");
		if (!escenario.empresa.getHistorialViajeCliente(cliente).isEmpty()) {
			fail("El cliente no deberia existir en la empresa por lo que el historial de viajes deberia estar vacio");
		}
	}
	
	@Test
	public void testGetPedidos_Clase1() {
		if (!escenario.empresa.getPedidos().isEmpty()) {
			fail("La coleccion de pedidos deberia estar vacia");
		}
	}
	
	@Test
	public void testGetUsuarioLogeado_Clase1() {
		assertEquals("El usuario logeado deberia ser null", null, escenario.empresa.getUsuarioLogeado());
	}
	
	@Test
	public void testGetVehiculos_Clase1() {
		if (!escenario.empresa.getVehiculos().isEmpty()) {
			fail("La coleccion de vehiculos deberia estar vacia");
		}
	}	
	
	@Test
	public void testGetVehiculosDesocupados_Clase1() {
		if (!escenario.empresa.getVehiculosDesocupados().isEmpty()) {
			fail("La coleccion de vehiculos deberia estar vacia, por lo tanto no deberia haber vehiculos desocupados");
		}
	}	
	
	@Test
	public void testGetViajesIniciados_Clase1() {
		if (!escenario.empresa.getViajesIniciados().isEmpty()) {
			fail("La coleccion de viajes deberia estar vacia, por lo tanto no deberia haber viajes iniciados");
		}
	}
	
	@Test
	public void testGetViajesTerminados_Clase1() {
		if (!escenario.empresa.getViajesTerminados().isEmpty()) {
			fail("La coleccion de viajes deberia estar vacia, por lo tanto no deberia haber viajes terminados");
		}
	}
	
	@Test
	public void testSetChoferes_Clase1() {
		HashMap<String,Chofer> hashMapOriginal = escenario.empresa.getChoferes();
		escenario.empresa.setChoferes(new HashMap<String,Chofer>());
		assertNotSame("La coleccion de choferes no cambio", hashMapOriginal, escenario.empresa.getChoferes());
	}
	
	@Test
	public void testSetChoferesDesocupados_Clase1() {
		ArrayList<Chofer> arrayListOriginal = escenario.empresa.getChoferesDesocupados();
		escenario.empresa.setChoferesDesocupados(new ArrayList<Chofer>());
		assertNotSame("La coleccion de choferes desocupados no cambio", arrayListOriginal, escenario.empresa.getChoferesDesocupados());
	}
	
	
	@Test
	public void testSetClientes_Clase1() {
		HashMap<String,Cliente> hashMapOriginal = escenario.empresa.getClientes();
		escenario.empresa.setClientes(new HashMap<String,Cliente>());
		assertNotSame("La coleccion de clientes no cambio", escenario.empresa.getClientes(), hashMapOriginal);
	}
	
	@Test
	public void testSetPedidos_Clase1() {
		HashMap<Cliente,Pedido> hashMapOriginal = escenario.empresa.getPedidos();
		escenario.empresa.setPedidos(new HashMap<Cliente,Pedido>());
		assertNotSame("La coleccion de pedidos no cambio", escenario.empresa.getPedidos(), hashMapOriginal);
	}
	
	@Test
	public void testSetUsuarioLogeado_Clase1() {
		Usuario usuarioLogeado = escenario.empresa.getUsuarioLogeado();
		escenario.empresa.setUsuarioLogeado(new Cliente("Esteban Gutierrez", "12345678", "estaban1200"));
		assertNotSame("El usuario logeado no cambio", escenario.empresa.getUsuarioLogeado(), usuarioLogeado);
	}
	
	@Test
	public void testSetVehiculos_Clase1() {
		HashMap<String,Vehiculo> hashMapOriginal = escenario.empresa.getVehiculos();
		escenario.empresa.setVehiculos(new HashMap<String,Vehiculo>());
		assertNotSame("La coleccion de vehiculos no cambio", escenario.empresa.getVehiculos(), hashMapOriginal);
	}
	
	@Test
	public void testSetVehiculosDesocupados_Clase1() {
		ArrayList<Vehiculo> ArrayListOriginal = escenario.empresa.getVehiculosDesocupados();
		escenario.empresa.setVehiculosDesocupados(new ArrayList<Vehiculo>());
		assertNotSame("La coleccion de vehiculos desocupados no cambio", escenario.empresa.getVehiculosDesocupados(), ArrayListOriginal);
	}
	
	@Test
	public void testSetViajesIniciados_Clase1() {
		HashMap<Cliente,Viaje> hashMapOriginal = escenario.empresa.getViajesIniciados();
		escenario.empresa.setViajesIniciados(new HashMap<Cliente,Viaje>());
		assertNotSame("La coleccion de viajes iniciados no cambio", escenario.empresa.getViajesIniciados(), hashMapOriginal);
	}
	
	@Test
	public void testSetViajesTerminados_Clase1() {
		ArrayList<Viaje> ArrayListOriginal = escenario.empresa.getViajesTerminados();
		escenario.empresa.setViajesTerminados(new ArrayList<Viaje>());
		assertNotSame("La coleccion de viajes terminados no cambio", escenario.empresa.getViajesTerminados(), ArrayListOriginal);
	}
	
	@Test
	public void testLogin_Clase1() {
		try {
			escenario.empresa.login("piojo_3000", "12345678");
			fail("Deberia tirar la excepcion UsuarioNoExisteException ya que la lista de clientes esta vacia");
		} catch(UsuarioNoExisteException e) {
		} catch (PasswordErroneaException e) {
			fail("Deberia tirar la excepcion UsuarioNoExisteException ya que la lista de clientes esta vacia");
		}
	}
	
	@Test
	public void testGetInstance_Clase1() {
		assertSame("Las referencias a empresa deberian ser iguales", escenario.empresa, Empresa.getInstance());
	}
	
	@Test
	public void testIsAdmin_Clase1() {
		assertEquals("Deberia ser falso", escenario.empresa.isAdmin(), false);
	}
	
}
