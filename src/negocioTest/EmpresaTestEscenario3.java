package negocioTest;

import static org.junit.Assert.*;
import static util.Constantes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ChoferNoDisponibleException;
import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.PedidoInexistenteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;

public class EmpresaTestEscenario3 {
	private Escenario3 escenario = new Escenario3();
	
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
			escenario.empresa.agregarChofer(new ChoferTemporario("45620890", "Marcos"));
			if(!escenario.empresa.getChoferes().containsKey("45620890"))
				fail("No se añadio a la coleccion de choferes");
		} catch (ChoferRepetidoException e) {
			fail("La coleccion de choferes esta vacia, no deberia entrar aqui");
		}
	}
	
	@Test
	public void testAgregarChofer_Clase2() {
		try {
			escenario.empresa.agregarChofer(new ChoferPermanente("987789", "Fangio", 1900, 0));
			fail("Deberia saltar la excepcion de chofer repetido");
		} catch (ChoferRepetidoException e) {
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
	public void testAgregarVehiculo_Clase2() {
		try {
			escenario.empresa.agregarVehiculo(new Moto("HH111HH"));
			fail("Deberia saltar la excepcion de vehiculo repetido");
		} catch(VehiculoRepetidoException e) {
		}
	}
	
	@Test 
	public void testAgregarPedido_Clase1() {
		try {
			escenario.empresa.agregarPedido(new Pedido(escenario.clientesTest.get(0), 3, true, true, 10,ZONA_SIN_ASFALTAR));
			fail("Deberia saltar la excepcion de cliente con pedido pendiente");
		} catch(ClienteConPedidoPendienteException e) {
		}catch(ClienteConViajePendienteException e) {
			fail("Deberia saltar la excepcion de cliente con pedido pendiente");
		} catch(ClienteNoExisteException e) {
			fail("Deberia saltar la excepcion de cliente con pedido pendiente");
		} catch (SinVehiculoParaPedidoException e) {
			fail("Deberia saltar la excepcion de cliente con pedido pendiente");
		}
	}
	
	@Test
	public void testVehiculoOrdenadoPorpedido() {
		ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		listaVehiculos.add(escenario.vehiculosTest.get(1));
		
		assertEquals("Las listas deberian ser iguales", listaVehiculos, escenario.empresa.vehiculosOrdenadosPorPedido(escenario.pedidosTest.get(0)));
	}
	
	@Test
	public void testGetPedidoDeCliente() {
		assertSame("La referencia al pedido deberia ser la misma del escenario", escenario.pedidosTest.get(0), escenario.empresa.getPedidoDeCliente(escenario.clientesTest.get(0)));
	}
	
	@Test
	public void testGetVehiculos() {
		HashMap<String, Vehiculo> hashMapVehiculos = new HashMap<String,Vehiculo>();
		hashMapVehiculos.put(escenario.vehiculosTest.get(0).getPatente(), escenario.vehiculosTest.get(0));
		hashMapVehiculos.put(escenario.vehiculosTest.get(1).getPatente(), escenario.vehiculosTest.get(1));
		hashMapVehiculos.put(escenario.vehiculosTest.get(2).getPatente(), escenario.vehiculosTest.get(2));
		
		assertEquals("Las colecciones deberian ser iguales", hashMapVehiculos, escenario.empresa.getVehiculos());
	}

	
	@Test
	public void testGetPedidos() {
		HashMap<Cliente, Pedido> hashMapPedidos = new HashMap<Cliente, Pedido>();
		hashMapPedidos.put(escenario.pedidosTest.get(0).getCliente(), escenario.pedidosTest.get(0));
		
		assertEquals("Las colecciones deberian ser iguales", hashMapPedidos, escenario.empresa.getPedidos());
	}
	
	@Test
	public void testIteratorVehiculos_Clase1() {
		HashMap<String, Vehiculo> hashMapVehiculos = new HashMap<String,Vehiculo>();
		hashMapVehiculos.put(escenario.vehiculosTest.get(0).getPatente(), escenario.vehiculosTest.get(0));
		hashMapVehiculos.put(escenario.vehiculosTest.get(1).getPatente(), escenario.vehiculosTest.get(1));
		hashMapVehiculos.put(escenario.vehiculosTest.get(2).getPatente(), escenario.vehiculosTest.get(2));
		
		Iterator<Vehiculo> vehiculosIteratorExpected = hashMapVehiculos.values().iterator();
		Iterator<Vehiculo> vehiculosIterator = escenario.empresa.iteratorVehiculos();
        while (vehiculosIteratorExpected.hasNext() && vehiculosIterator.hasNext()) {
        	Vehiculo elem1 = vehiculosIteratorExpected.next();
        	Vehiculo elem2 = vehiculosIterator.next();
            assertEquals("No son iguales los elementos del iterator", elem1, elem2);
        }
        
        if ((vehiculosIterator.hasNext() && !vehiculosIteratorExpected.hasNext())
        	|| (!vehiculosIterator.hasNext() && vehiculosIteratorExpected.hasNext())) {
        	fail("Los iterator no tienen la misma cantidad de elementos");
        }
	}
	
	@Test
	public void testIteratorPedidos_Clase1() {
		HashMap<Cliente, Pedido> hashMapPedidos = new HashMap<Cliente, Pedido>();
		hashMapPedidos.put(escenario.pedidosTest.get(0).getCliente(), escenario.pedidosTest.get(0));
		
		Iterator<Pedido> pedidosIteratorExpected = hashMapPedidos.values().iterator();
		Iterator<Pedido> pedidosIterator = escenario.empresa.iteratorPedidos();
        while (pedidosIteratorExpected.hasNext() && pedidosIterator.hasNext()) {
        	Pedido elem1 = pedidosIteratorExpected.next();
        	Pedido elem2 = pedidosIterator.next();
            assertEquals("No son iguales los elementos del iterator", elem1, elem2);
        }
        
        if ((pedidosIterator.hasNext() && !pedidosIteratorExpected.hasNext())
        	|| (!pedidosIterator.hasNext() && pedidosIteratorExpected.hasNext())) {
        	fail("Los iterator no tienen la misma cantidad de elementos");
        }
	}
}
