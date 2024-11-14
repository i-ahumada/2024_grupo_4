package negocioTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import excepciones.ChoferNoDisponibleException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.PasswordErroneaException;
import excepciones.PedidoInexistenteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;

import static util.Constantes.*;

public class EmpresaTestEscenario2 {
	private Escenario2 escenario = new Escenario2();
	
	@Before
	public void setUp() {
		escenario.setUp();
		
	}
	
	@After
	public void tearDown() {
		escenario.tearDown();
	}
	
	@Test
	public void testAgregarCliente_Clase1() { 
		try {
			escenario.empresa.agregarCliente("Tesla", "1234", "Nikola Tesla");
			if (!escenario.empresa.getClientes().containsKey("Tesla")) {
				fail("Se añadio a la coleccion de clientes cuando ya deberia estar");
			}
		} catch(UsuarioYaExisteException e) {
		}
	}
	
	@Test
	public void testValidarPedido_Clase1() {
		Cliente cliente = new Cliente("Esteban Gutierrez", "12345678", "estaban1200");
		boolean res = escenario.empresa.validarPedido(new  Pedido(cliente, 5, true, true, 10, ZONA_STANDARD));
		if (res != false) {
			fail("El resultado de validarPedido debia ser falso");
		}

	}
	
	@Test
	public void testCrearViaje_Clase1() {
		Pedido pedido2 = new Pedido(escenario.clientesTest.get(1), 4, false, true, 1000, ZONA_STANDARD);
	    try {
			escenario.empresa.agregarPedido(pedido2);
		} catch(ClienteConPedidoPendienteException | ClienteConViajePendienteException 
				| ClienteNoExisteException | SinVehiculoParaPedidoException e) {
		}
		try {
			escenario.empresa.crearViaje(pedido2, escenario.choferesTest.get(1), escenario.vehiculosTest.get(1));
			fail("Debio salir por alguna excepcion");
		} catch (PedidoInexistenteException e) {
			fail("Tendria que salir por vehiculo no valido");
		} catch (ChoferNoDisponibleException e) {
			fail("Tendria que salir por vehiculo no valido");
		} catch (VehiculoNoDisponibleException e) {
			fail("Tendria que salir por vehiculo no valido");
		} catch (VehiculoNoValidoException e) {
			
		} catch (ClienteConViajePendienteException e) {
			fail("Tendria que salir por vehiculo no valido");
		}
	}	
	
	@Test
	public void testCrearViaje_Clase2() {
		Pedido pedido2 = new Pedido(escenario.clientesTest.get(1), 3, false, true, 1000, ZONA_STANDARD);
	    try {
			escenario.empresa.agregarPedido(pedido2);
		} catch(ClienteConPedidoPendienteException | ClienteConViajePendienteException 
				| ClienteNoExisteException | SinVehiculoParaPedidoException e) {
		}
		try {
			escenario.empresa.crearViaje(pedido2, escenario.choferesTest.get(1), escenario.vehiculosTest.get(0));
			fail("Debio salir por alguna excepcion");
		} catch (PedidoInexistenteException e) {
			fail("Tendria que salir por vehiculo no disponible");
		} catch (ChoferNoDisponibleException e) {
			fail("Tendria que salir por vehiculo no disponible");
		} catch (VehiculoNoDisponibleException e) {
			
		} catch (VehiculoNoValidoException e) {
			fail("Tendria que salir por vehiculo no disponible");			
		} catch (ClienteConViajePendienteException e) {
			fail("Tendria que salir por vehiculo no disponible");
		}
	}
	
	@Test
	public void testCrearViaje_Clase3() {
		Pedido pedido2 = new Pedido(escenario.clientesTest.get(1), 3, false, true, 1000, ZONA_STANDARD);
	    try {
			escenario.empresa.agregarPedido(pedido2);
		} catch(ClienteConPedidoPendienteException | ClienteConViajePendienteException 
				| ClienteNoExisteException | SinVehiculoParaPedidoException e) {
		}
		
		try {
			escenario.empresa.crearViaje(pedido2, escenario.choferesTest.get(0), escenario.vehiculosTest.get(0));
			fail("Debio salir por excepcion de chofer no disponible");
		} catch (PedidoInexistenteException e) {
			fail("Debio salir por excepcion de chofer no disponible");
		} catch (ChoferNoDisponibleException e) {
			
		} catch (VehiculoNoDisponibleException e) {
			fail("Debio salir por excepcion de chofer no disponible");
		} catch (VehiculoNoValidoException e) {
			fail("Debio salir por excepcion de chofer no disponible");
		} catch (ClienteConViajePendienteException e) {
			fail("No es posible, ya que la lista de clientes esta vacia y no hay viajes");
		}
	}
	
	
	@Test
	public void testLogin_Clase1() {
		try {
			Usuario usuario = escenario.empresa.login("Tesla", "1234");
			if (!usuario.getNombreUsuario().equals("Tesla") || !usuario.getPass().equals("1234")) {
				fail("El usuario retornado deberia ser Tesla");
			}
		} catch(UsuarioNoExisteException e) {
			fail("Deberia retornar el usuario");
		} catch (PasswordErroneaException e) {
			fail("Deberia retornar el usuario");
		}
	}
	
	@Test
	public void testLogin_Clase2() {
		try {
			Usuario usuario = escenario.empresa.login("Tesla", "999");
			fail("Deberia tirar la excepcion PasswordErroneaException ya que el usuario Tesla existe");
		} catch(UsuarioNoExisteException e) {
			fail("Deberia tirar la excepcion PasswordErroneaException ya que el usuario Tesla existe");
		} catch (PasswordErroneaException e) {
		}
	}
	
	@Test
	public void testGetChoferes_Clase1() {
		HashMap<String, Chofer> choferesHashMap = new HashMap<String, Chofer>();
		choferesHashMap.put(escenario.choferesTest.get(0).getDni(), escenario.choferesTest.get(0));
		choferesHashMap.put(escenario.choferesTest.get(1).getDni(), escenario.choferesTest.get(1));
		assertEquals("El hashmap de choferes no contiene los elementos que deberia", choferesHashMap, escenario.empresa.getChoferes());
	}
	
	@Test
	public void testIteratorChoferes_Clase1() {
		HashMap<String, Chofer> choferesHashMap = new HashMap<String, Chofer>();
		choferesHashMap.put(escenario.choferesTest.get(0).getDni(), escenario.choferesTest.get(0));
		choferesHashMap.put(escenario.choferesTest.get(1).getDni(), escenario.choferesTest.get(1));
		
		Iterator<Chofer> choferesIteratorExpected = choferesHashMap.values().iterator();
		Iterator<Chofer> choferesIterator = escenario.empresa.iteratorChoferes();
        while (choferesIteratorExpected.hasNext() && choferesIterator.hasNext()) {
            Chofer elem1 = choferesIteratorExpected.next();
            Chofer elem2 = choferesIterator.next();
            assertEquals("No son iguales los elementos del iterator", elem1, elem2);
        }
        
        if ((choferesIterator.hasNext() && !choferesIteratorExpected.hasNext())
        	|| (!choferesIterator.hasNext() && choferesIteratorExpected.hasNext())) {
        	fail("Los iterator no tienen la misma cantidad de elementos");
        }
	}
	
	@Test
	public void testIteratorClientes_Clase1() {
		HashMap<String, Cliente> clientesHashMap = new HashMap<String, Cliente>();
		clientesHashMap.put(escenario.clientesTest.get(0).getNombreUsuario(), escenario.clientesTest.get(0));
		clientesHashMap.put(escenario.clientesTest.get(1).getNombreUsuario(), escenario.clientesTest.get(1));
		
		Iterator<Cliente> clientesIteratorExpected = clientesHashMap.values().iterator();
		Iterator<Cliente> clientesIterator = escenario.empresa.iteratorClientes();
        while (clientesIteratorExpected.hasNext() && clientesIterator.hasNext()) {
            Cliente elem1 = clientesIteratorExpected.next();
            Cliente elem2 = clientesIterator.next();
            assertEquals("No son iguales los elementos del iterator", elem1, elem2);
        }
        
        if ((clientesIterator.hasNext() && !clientesIteratorExpected.hasNext())
        	|| (!clientesIterator.hasNext() && clientesIteratorExpected.hasNext())) {
        	fail("Los iterator no tienen la misma cantidad de elementos");
        }	
     }
	
	@Test
	public void testGetVehiculosDesocupados() {
		ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		listaVehiculos.add(escenario.vehiculosTest.get(1));
		
		assertEquals("Las listas deberian ser iguales", listaVehiculos, escenario.empresa.getVehiculosDesocupados());
	}
	
}
