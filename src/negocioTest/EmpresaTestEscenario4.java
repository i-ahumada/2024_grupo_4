package negocioTest;

import static util.Constantes.ZONA_SIN_ASFALTAR;
import static util.Constantes.ZONA_STANDARD;

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
import excepciones.ClienteSinViajePendienteException;
import excepciones.PasswordErroneaException;
import excepciones.PedidoInexistenteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.SinViajesException;
import excepciones.UsuarioNoExisteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;

public class EmpresaTestEscenario4 {
	private Escenario4 escenario = new Escenario4();
	
	@Before
	public void setUp() {
		escenario.setUp();
		
	}
	
	@After
	public void tearDown() {
		escenario.tearDown();
	}
	
	@Test 
	public void testAgregarPedido_Clase1() {
		try {
			escenario.empresa.agregarPedido(new Pedido(escenario.clientesTest.get(2), 1, false, false, 10,ZONA_SIN_ASFALTAR));
			fail("Deberia saltar la excepcion de cliente con viaje pendiente");
		} catch(ClienteConPedidoPendienteException e) {
			fail("Deberia saltar la excepcion de cliente con viaje pendiente");
		} catch(ClienteConViajePendienteException e) {
		} catch(ClienteNoExisteException e) {
			fail("Deberia saltar la excepcion de cliente con viaje pendiente");
		} catch (SinVehiculoParaPedidoException e) {
			fail("Deberia saltar la excepcion de cliente con viaje pendiente");
		}
	}
	
	@Test 
	public void testCalificacionDeChofer_Clase1() {
		try {
			assertEquals("La calificacion promedio esta mal calculada",4.0, escenario.empresa.calificacionDeChofer(escenario.choferesTest.get(1)),0.1);
		} catch (SinViajesException e) {
			fail("El chofer tiene viajes por lo que no deberia saltar esta excepcion");
		}
	}
	
	@Test 
	public void testCalificacionDeChofer_Clase2() {
		try {
			escenario.empresa.calificacionDeChofer(new ChoferTemporario("23900189" , "Lucas"));
			fail("El chofer no tiene viajes por lo que deberia salir por la excepcion SinViajes");			
		} catch (SinViajesException e) {
		}
	}
	
	@Test
	public void testPagarYFinalizarViaje_Clase1() {
		Cliente clienteLog = escenario.clientesTest.get(2);
		Viaje clienteViaje = escenario.empresa.getViajesIniciados().get(clienteLog);
	   	try {
			escenario.empresa.login(clienteLog.getNombreUsuario(), clienteLog.getPass());
		} catch (UsuarioNoExisteException | PasswordErroneaException e) {
		}
	   	
	   	try {
			escenario.empresa.pagarYFinalizarViaje(2); 
		} catch (ClienteSinViajePendienteException e) {
		} 
	   	
	   	if (!escenario.empresa.getViajesTerminados().contains(clienteViaje)) {
	   		fail("El viaje no se agrego");
	   	}
	   	
	   	if (clienteViaje.getCalificacion() != 2) {
	   		fail("El viaje no se califico");
	   	}
	   	
	}
	
	@Test
	public void testPagarYFinalizarViaje_Clase2() {
		Cliente clienteLog = escenario.clientesTest.get(0);

		try {
			escenario.empresa.login(clienteLog.getNombreUsuario(), clienteLog.getPass());
		} catch (UsuarioNoExisteException | PasswordErroneaException e) {
		}
	   	
	   	try {
			escenario.empresa.pagarYFinalizarViaje(2); 
			fail("Deberia haber saltado la excepcion de cliente sin viaje pendiente");
		} catch (ClienteSinViajePendienteException e) {
		} 
	   	
	   	
	}
	
	@Test
	public void testGetHistorialViajeChofer_Clase1() {
		ArrayList<Viaje> historialEsperado = new ArrayList<Viaje>();
		historialEsperado.add(escenario.viajesTerminadosTest.get(1));
		historialEsperado.add(escenario.viajesTerminadosTest.get(2));
		
		assertEquals("Las colecciones deberian ser iguales", historialEsperado, escenario.empresa.getHistorialViajeChofer(escenario.choferesTest.get(1)));
	}
	
	@Test
	public void testGetHistorialViajeChofer_Clase2() {
		ArrayList<Viaje> historialEsperado = new ArrayList<Viaje>();
		
		assertEquals("Las colecciones deberian ser iguales y estar vacias", historialEsperado, escenario.empresa.getHistorialViajeChofer(escenario.choferesTest.get(2)));
	}

	@Test
	public void testGetHistorialViajeCliente_Clase1() {
		ArrayList<Viaje> historialEsperado = new ArrayList<Viaje>();
		historialEsperado.add(escenario.viajesTerminadosTest.get(0));
		historialEsperado.add(escenario.viajesTerminadosTest.get(2));
		
		assertEquals("Las colecciones deberian ser iguales", historialEsperado, escenario.empresa.getHistorialViajeCliente(escenario.clientesTest.get(1)));
	}
	
	@Test
	public void testGetHistorialViajeCliente_Clase2() {
		ArrayList<Viaje> historialEsperado = new ArrayList<Viaje>();
		
		assertEquals("Las colecciones deberian ser iguales y estar vacias", historialEsperado, escenario.empresa.getHistorialViajeCliente(escenario.clientesTest.get(3)));
	}
	
	
	@Test
	public void testGetTotalSalarios_Clase1() {
		assertEquals("No se calcula bien el total de salarios", 2270400.0, escenario.empresa.getTotalSalarios(), 0.1);
	}
	
	@Test
	public void testGetViajesIniciados_Clase1() {
		HashMap<Cliente, Viaje> hashMapEsperado = new HashMap<Cliente, Viaje>();
		hashMapEsperado.put(escenario.viajesIniciadosTest.get(0).getPedido().getCliente(), escenario.viajesIniciadosTest.get(0));
		hashMapEsperado.put(escenario.viajesIniciadosTest.get(1).getPedido().getCliente(), escenario.viajesIniciadosTest.get(1));
		hashMapEsperado.put(escenario.viajesIniciadosTest.get(2).getPedido().getCliente(), escenario.viajesIniciadosTest.get(2));
		
		assertEquals("Las colecciones deberian ser iguales", hashMapEsperado, escenario.empresa.getViajesIniciados());
	}
	
	@Test
	public void testGetViajesTerminados_Clase1() {
		assertEquals("Las colecciones deberian ser iguales", escenario.viajesTerminadosTest, escenario.empresa.getViajesTerminados());
	}
	
	@Test
	public void testIteratorViajesIniciados_Clase1() {
		HashMap<Cliente, Viaje> hashMapEsperado = new HashMap<Cliente, Viaje>();
		hashMapEsperado.put(escenario.viajesIniciadosTest.get(0).getPedido().getCliente(), escenario.viajesIniciadosTest.get(0));
		hashMapEsperado.put(escenario.viajesIniciadosTest.get(1).getPedido().getCliente(), escenario.viajesIniciadosTest.get(1));
		hashMapEsperado.put(escenario.viajesIniciadosTest.get(2).getPedido().getCliente(), escenario.viajesIniciadosTest.get(2));
		
		Iterator<Viaje> viajesIteratorExpected = hashMapEsperado.values().iterator();
		Iterator<Viaje> viajesIterator = escenario.empresa.iteratorViajesIniciados();
        while (viajesIteratorExpected.hasNext() && viajesIterator.hasNext()) {
        	Viaje elem1 = viajesIteratorExpected.next();
        	Viaje elem2 = viajesIterator.next();
            assertEquals("No son iguales los elementos del iterator", elem1, elem2);
        }
        
        if ((viajesIterator.hasNext() && !viajesIteratorExpected.hasNext())
        	|| (!viajesIterator.hasNext() && viajesIteratorExpected.hasNext())) {
        	fail("Los iterator no tienen la misma cantidad de elementos");
        }
	}
	
	@Test
	public void testLogout_Clase1() {
		try {
			Usuario usuario = escenario.empresa.login("Tesla", "1234");
			if (!usuario.getNombreUsuario().equals("Tesla") || !usuario.getPass().equals("1234")) {
				fail("El usuario retornado deberia ser Tesla");
			}
			escenario.empresa.logout();
			assertEquals("El usuario loggeado deberia ser nulo ", null, escenario.empresa.getUsuarioLogeado());
		} catch(UsuarioNoExisteException e) {
			fail("Deberia retornar el usuario");
		} catch (PasswordErroneaException e) {
			fail("Deberia retornar el usuario");
		}
	}
	
}
