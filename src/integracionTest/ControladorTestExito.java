package integracionTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import guiTest.TestUtil;
import javax.swing.JButton;
import modeloDatos.*;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;
import util.Constantes;

public class ControladorTestExito {
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
	public void testLoginExito()
	{
		escenario.controlador.login();
		Cliente cliente = escenario.empresa.getClientes().get(escenario.vista.getUsserName());
	        
		List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
				new SimpleEntry<>("Cliente logeado debería ser distinto de null", cliente != null),
				new SimpleEntry<>("Contrasena cliente logeado incorrecta", escenario.vista.getPassword().equals(cliente.getPass()))
				);
	        
		TestUtil.reportarErrores(aserciones);
		
	}
	
	@Test
	public void testRegistrarExito()
	{
		escenario.controlador.registrar();
		Cliente cliente = escenario.empresa.getClientes().get(escenario.vista.getRegUsserName());
        
        List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
            new SimpleEntry<>("Cliente nuevo debería ser distinto de null", cliente != null),
            new SimpleEntry<>("Nombre real cliente nuevo incorrecto", escenario.vista.getRegNombreReal().equals(cliente.getNombreReal())),
            new SimpleEntry<>("Contrasena cliente nuevo incorrecta", escenario.vista.getRegPassword().equals(cliente.getPass()))
        );
        
        TestUtil.reportarErrores(aserciones);
	}
	
	@Test
	public void testLogoutCliente() {
		escenario.controlador.logout();
		
		PersistenciaBIN persistenciaMock =  mock(PersistenciaBIN.class);
		escenario.controlador.setPersistencia(persistenciaMock);
	    escenario.controlador.escribir();
	    try {
			verify(persistenciaMock).escribir(any());
		} catch (IOException e) {
			fail("no deberia lanzar execepcion al escribir con exito");
		}
		
		assertNull("El usuario logeado deberia ser null",escenario.empresa.getUsuarioLogeado());
	}
	
	@Test
	public void testLogoutAdmin() {
		try {
			escenario.empresa.login("admin", "admin");
		} catch (UsuarioNoExisteException | PasswordErroneaException e) {
			e.printStackTrace();
		}
		escenario.controlador.logout();
		
		PersistenciaBIN persistenciaMock =  mock(PersistenciaBIN.class);
		escenario.controlador.setPersistencia(persistenciaMock);
	    escenario.controlador.escribir();
	    try {
			verify(persistenciaMock).escribir(any());
		} catch (IOException e) {
			fail("no deberia lanzar execepcion al escribir con exito");
		}
		
		assertNull("El usuario logeado deberia ser null",escenario.empresa.getUsuarioLogeado());
	}

	@Test
	public void testLogoutNull() {
		escenario.empresa.logout();
		escenario.controlador.logout();
		
		PersistenciaBIN persistenciaMock =  mock(PersistenciaBIN.class);
		escenario.controlador.setPersistencia(persistenciaMock);
	    escenario.controlador.escribir();
	    try {
			verify(persistenciaMock).escribir(any());
		} catch (IOException e) {
			fail("no deberia lanzar execepcion al escribir con exito");
		}
		
		assertNull("El usuario logeado deberia ser null",escenario.empresa.getUsuarioLogeado());
	}
	
	@Test
	public void testCalificaryPagarExito()
	{
        escenario.controlador.calificarPagar();
        
        List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
            new SimpleEntry<>("Debería haber exactamente 1 viaje terminado", escenario.empresa.getViajesTerminados().size() == 1),
            new SimpleEntry<>("Viaje terminado con calificación incorrecta", escenario.vista.getCalificacion() == escenario.empresa.getViajesTerminados().get(0).getCalificacion()));
        
        TestUtil.reportarErrores(aserciones);
	}
	
	@Test
	public void testNuevoChoferExito()
	{
		escenario.controlador.nuevoChofer();
		Chofer choferNuevo = escenario.empresa.getChoferes().get(escenario.vista.getDNIChofer());
        
        List<SimpleEntry<String, Boolean>> aserciones = new ArrayList<>(); // Cambia a ArrayList
        
        aserciones.add(new SimpleEntry<>("Chofer nuevo debería ser distinto de null", choferNuevo != null));
        aserciones.add(new SimpleEntry<>("Chofer nuevo con dni incorrecto", escenario.vista.getDNIChofer().equals(choferNuevo.getDni())));
        aserciones.add(new SimpleEntry<>("Chofer nuevo nombre incorrecto", escenario.vista.getNombreChofer().equals(choferNuevo.getNombre())));

        if (escenario.vista.getTipoChofer().equals(Constantes.PERMANENTE)) {
            aserciones.add(new SimpleEntry<>("Chofer cantidad de hijos incorrecta", 
                escenario.vista.getHijosChofer() == ((ChoferPermanente) choferNuevo).getCantidadHijos()));
            aserciones.add(new SimpleEntry<>("Chofer nuevo anio incorrecto", 
                escenario.vista.getAnioChofer() == ((ChoferPermanente) choferNuevo).getAnioIngreso()));
        }

        TestUtil.reportarErrores(aserciones);
	}
	
	@Test
	public void testNuevoVehiculoExito()
	{
		escenario.controlador.nuevoVehiculo();
		Vehiculo vehiculoNuevo = escenario.empresa.getVehiculos().get(escenario.vista.getPatente());
        
        List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
            new SimpleEntry<>("Vehículo nuevo debería ser distinto de null", vehiculoNuevo != null),
            new SimpleEntry<>("Vehículo nuevo debería ser de tipo " + escenario.vista.getTipoVehiculo(), vehiculoNuevo.getClass().getSimpleName().equalsIgnoreCase(escenario.vista.getTipoVehiculo())),
            new SimpleEntry<>("Vehículo nuevo con patente incorrecta", escenario.vista.getPatente().equals(vehiculoNuevo.getPatente())),
            new SimpleEntry<>("Vehículo nuevo con cantidad de plazas incorrecta", escenario.vista.getPlazas() == vehiculoNuevo.getCantidadPlazas()),
            new SimpleEntry<>("Vehículo nuevo con tolerancia de mascotas incorrecta", escenario.vista.isVehiculoAptoMascota() == vehiculoNuevo.isMascota())
        );
        
        TestUtil.reportarErrores(aserciones);
	}
	
	@Test
	public void testNuevoPedidoExito()
	{
		Cliente cliente = escenario.empresa.getClientes().get("Eloncito");
		try {
			escenario.empresa.login(escenario.vista.getUsserName(), escenario.vista.getPassword());
		} catch (UsuarioNoExisteException | PasswordErroneaException e) {
			fail("No inicio sesion para agregar pedido");
		}
		escenario.controlador.nuevoPedido();
        Pedido pedidoNuevo = escenario.empresa.getPedidoDeCliente(cliente);
        
        List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
            new SimpleEntry<>("Pedido nuevo debería ser distinto de null", pedidoNuevo != null),
            new SimpleEntry<>("Pedido nuevo con cliente incorrecto", escenario.vista.getUsserName().equals(pedidoNuevo.getCliente().getNombreUsuario())),
            new SimpleEntry<>("Pedido nuevo con cantidad de pasajeros incorrecta", escenario.vista.getCantidadPax() == pedidoNuevo.getCantidadPasajeros()),
            new SimpleEntry<>("Pedido nuevo con tolerancia baúl incorrecta", escenario.vista.isPedidoConBaul() == pedidoNuevo.isBaul()),
            new SimpleEntry<>("Pedido nuevo con cantidad km incorrecta", escenario.vista.getCantKm() == pedidoNuevo.getKm()),
            new SimpleEntry<>("Pedido nuevo con tolerancia de mascotas incorrecta", escenario.vista.isVehiculoAptoMascota() == pedidoNuevo.isMascota())
        );
        
        TestUtil.reportarErrores(aserciones);
	}
	
	@Test
	public void testNuevoViajeExito()
	{
		ActionEvent eventNuevoViaje = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.NUEVO_VIAJE);
        escenario.controlador.actionPerformed(eventNuevoViaje);
        
        Cliente cliente = escenario.empresa.getClientes().get("cr7");
        Viaje viajeNuevo = escenario.empresa.getViajesIniciados().get(cliente);
        
        List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
            new SimpleEntry<>("Viaje iniciado nuevo debería ser distinto de null", viajeNuevo != null),
            new SimpleEntry<>("Pedido de viaje iniciado no es el seleccionado en vista", escenario.vista.getPedidoSeleccionado().equals(viajeNuevo.getPedido())),
            new SimpleEntry<>("Chofer de viaje iniciado no es el seleccionado en vista", escenario.vista.getChoferDisponibleSeleccionado().equals(viajeNuevo.getChofer())),
            new SimpleEntry<>("Vehículo de viaje iniciado no es el seleccionado en vista", escenario.vista.getVehiculoDisponibleSeleccionado().equals(viajeNuevo.getVehiculo()))
        );
        
        TestUtil.reportarErrores(aserciones);
	}
	
	@Test
	public void testLeerExito() {
	    // Mock de PersistenciaBIN
	    PersistenciaBIN persistenciaMock = mock(PersistenciaBIN.class);
	    escenario.controlador.setPersistencia(persistenciaMock);
	    EmpresaDTO empresaDTO = new EmpresaDTO();
	    
	    try {
			when(persistenciaMock.leer()).thenReturn(empresaDTO);
		} catch (ClassNotFoundException | IOException e) {
	
		}

	    escenario.controlador.leer();
	    try {
			verify(persistenciaMock).leer();
		} catch (ClassNotFoundException | IOException e) {
			fail("no deberia lanzar execepcion al leer con exito");
		}

	}

	@Test
	public void testEscribirExito()
	{
		PersistenciaBIN persistenciaMock =  mock(PersistenciaBIN.class);
		escenario.controlador.setPersistencia(persistenciaMock);
	    escenario.controlador.escribir();
	    try {
			verify(persistenciaMock).escribir(any());
		} catch (IOException e) {
			fail("no deberia lanzar execepcion al escribir con exito");
		}
	}
	
	
	
}
