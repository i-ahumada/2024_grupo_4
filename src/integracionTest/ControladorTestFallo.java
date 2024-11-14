package integracionTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.PasswordErroneaException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioNoExisteException;
import modeloDatos.*;
import persistencia.PersistenciaBIN;
import util.Constantes;
import util.Mensajes;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import guiTest.TestUtil;
import java.io.IOException;


public class ControladorTestFallo {
	private Escenario escenario = new Escenario();
	
    @Before
    public void setUp()
    {
    	try {
			this.escenario.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@After
	public void tearDown()
	{
		this.escenario.tearDown();
	}
	
	@Test
	public void testLoginFalloPassword() {
		escenario.vista.setUsserName("Messi");
		escenario.controlador.login();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null", mensaje);
		assertEquals("Mensaje de fallo de login incorrecto",  Mensajes.USUARIO_DESCONOCIDO.getValor(), mensaje);
	}

	@Test
	public void testRegistrarFalloUsuarioRepetido() {
		escenario.vista.setRegUsserName(escenario.vista.getUsserName());
		escenario.controlador.registrar();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null",mensaje);
		assertEquals("Mensaje de fallo de registro incorrecto", Mensajes.USUARIO_REPETIDO.getValor(), mensaje);
	}

	@Test
	public void testRegistrarFalloConfirmPassNoCoincide() {
		escenario.vista.setRegConfirmPassword("$$$");
		escenario.controlador.registrar();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null",mensaje);
		assertEquals("Mensaje de fallo de registro incorrecto", Mensajes.PASS_NO_COINCIDE.getValor(), mensaje);
	}

	@Test
	public void testCalificaryPagarFallo()
	{
		try {
			escenario.empresa.logout();
			escenario.empresa.login(escenario.vista.getUsserName(), escenario.vista.getPassword());
		} catch (UsuarioNoExisteException | PasswordErroneaException e) {
			e.printStackTrace();
		}
		escenario.controlador.calificarPagar();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null",mensaje);
		assertEquals("Mensaje de fallo de registro incorrecto", Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor(), mensaje);			
	}
	
	@Test
	public void testNuevoChoferFallo()
	{
		escenario.vista.setDNIChofer("44555666");
		escenario.controlador.nuevoChofer();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null",mensaje);
		assertEquals("Mensaje de fallo de registro incorrecto", Mensajes.CHOFER_YA_REGISTRADO.getValor(), mensaje);	
	}
	
	@Test
	public void testNuevoVehiculoFallo()
	{
		escenario.vista.setPatente("AA777AA");
		escenario.controlador.nuevoVehiculo();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null",mensaje);
		assertEquals("Mensaje de fallo de registro incorrecto", Mensajes.VEHICULO_YA_REGISTRADO.getValor(), mensaje);	
	}

	@Test
	public void testNuevoPedidoFalloViajePendiente()
	{
		escenario.controlador.nuevoPedido();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null",mensaje);
		assertEquals("Mensaje de fallo de registro incorrecto", Mensajes.CLIENTE_CON_VIAJE_PENDIENTE.getValor(), mensaje);	
	}
	
	@Test
	public void testNuevoPedidoFalloPedidoPendiente()
	{
		try {
			escenario.empresa.login("cr7", "misterChampions");
		} catch (UsuarioNoExisteException | PasswordErroneaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		escenario.controlador.nuevoPedido();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null",mensaje);
		assertEquals("Mensaje de fallo de registro incorrecto", Mensajes.CLIENTE_CON_PEDIDO_PENDIENTE.getValor(), mensaje);	
	}

	@Test
	public void testNuevoViajeFalloVehiculoNoDisponible()
	{

		Vehiculo v = escenario.empresa.getVehiculos().get("AA777AA");
		escenario.vista.setVehiculoDisponibleSeleccionado(v);
		escenario.controlador.nuevoViaje();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null",mensaje);
		assertEquals("Mensaje de fallo de registro incorrecto", Mensajes.VEHICULO_NO_DISPONIBLE.getValor(), mensaje);
		
	}
	
	@Test
	public void testNuevoViajeFalloChoferNoDisponible()
	{

		Chofer c = escenario.empresa.getChoferes().get("44555666");
		escenario.vista.setChoferDisponibleSeleccionado(c);
		escenario.controlador.nuevoViaje();
		String mensaje = escenario.optionPane.getMensaje();
		assertNotNull("Mensaje de option pane deberia ser distinto de null",mensaje);
		assertEquals("Mensaje de fallo de registro incorrecto", Mensajes.CHOFER_NO_DISPONIBLE.getValor(), mensaje);
		
	}
	
	@Test
	public void testEscribirFallo() {    
	    PersistenciaBIN persistenciaMock = mock(PersistenciaBIN.class);
	    escenario.controlador.setPersistencia(persistenciaMock);
	    try {
			doThrow(new IOException("Error al escribir en el archivo")).when(persistenciaMock).escribir(any());
		} catch (IOException e) {
		}
	    escenario.controlador.escribir();
	    assertEquals("Deberia haber lanzado excepcion IO", "Error al escribir en el archivo", escenario.optionPane.getMensaje());
	}

	
	@Test
	public void testLeerFallo()
	{	
	    PersistenciaBIN persistenciaMock = mock(PersistenciaBIN.class);
	    escenario.controlador.setPersistencia(persistenciaMock);
	    try {
			doThrow(new IOException("Error al escribir en el archivo")).when(persistenciaMock).leer();
		} catch (IOException | ClassNotFoundException e) {
		}
	    escenario.controlador.leer();
	    assertEquals("Deberia haber lanzado excepcion IO", "Error al escribir en el archivo", escenario.optionPane.getMensaje());
	}
	

	
}