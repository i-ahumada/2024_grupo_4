package integracionTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import guiTest.TestUtil;
import modeloDatos.Administrador;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import util.Constantes; 

public class ControladorTestActionPerformed {
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
    	escenario.tearDown();
    }
    
    @Test
    public void testActionPerformed_LOGIN() {
        ActionEvent eventLogin = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.LOGIN);
        escenario.controlador.actionPerformed(eventLogin);
        
        Cliente cliente = escenario.empresa.getClientes().get(escenario.vista.getUsserName());
        
        List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
            new SimpleEntry<>("Cliente logeado debería ser distinto de null", cliente != null),
            new SimpleEntry<>("Contrasena cliente logeado incorrecta", escenario.vista.getPassword().equals(cliente.getPass()))
        );
        
        TestUtil.reportarErrores(aserciones);
    }

    @Test
    public void testActionPerformed_REGISTRAR() {
        ActionEvent eventRegistrar = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.REG_BUTTON_REGISTRAR);
        escenario.controlador.actionPerformed(eventRegistrar);
        
        Cliente cliente = escenario.empresa.getClientes().get(escenario.vista.getRegUsserName());
        
        List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
            new SimpleEntry<>("Cliente nuevo debería ser distinto de null", cliente != null),
            new SimpleEntry<>("Nombre real cliente nuevo incorrecto", escenario.vista.getRegNombreReal().equals(cliente.getNombreReal())),
            new SimpleEntry<>("Contrasena cliente nuevo incorrecta", escenario.vista.getRegPassword().equals(cliente.getPass()))
        );
        
        TestUtil.reportarErrores(aserciones);
    }

	@Test
	public void testActionPerformed_CERRARSESIONCLIENTE() {
		ActionEvent eventCerrarSesion= new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.CERRAR_SESION_CLIENTE);
		escenario.controlador.actionPerformed(eventCerrarSesion);
		Cliente cliente = (Cliente)escenario.empresa.getUsuarioLogeado();
		assertNull("El usuario logeado deberia ser null",cliente);
	}
	
	@Test
	public void testActionPerformed_CERRARSESIONADMIN() {
		ActionEvent eventCerrarSesion= new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.CERRAR_SESION_ADMIN);
		escenario.empresa.logout();
		try {
			escenario.empresa.login("admin", "admin");
		} catch (Exception e) {
			fail("No deberia entrar, ya que el administrador ya esta en el sistema");
		}
		escenario.controlador.actionPerformed(eventCerrarSesion);
		Administrador admin = (Administrador)escenario.empresa.getUsuarioLogeado();
		assertNull("El usuario logeado deberia ser null",admin);
	}


    
    @Test
    public void testActionPerformed_CALIFICARPAGAR() {
        ActionEvent eventCalificarPagar = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.CALIFICAR_PAGAR);
        escenario.controlador.actionPerformed(eventCalificarPagar);
        
        List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
            new SimpleEntry<>("Debería haber exactamente 1 viaje terminado", escenario.empresa.getViajesTerminados().size() == 1),
            new SimpleEntry<>("Viaje terminado con calificación incorrecta", escenario.vista.getCalificacion() == escenario.empresa.getViajesTerminados().get(0).getCalificacion()));
        
        TestUtil.reportarErrores(aserciones);
    }

    @Test
    public void testActionPerformed_NUEVOPEDIDO() {
        ActionEvent eventNuevoPedido = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.NUEVO_PEDIDO);
        Cliente cliente = escenario.empresa.getClientes().get("Eloncito");
        
        try {
            escenario.empresa.login(escenario.vista.getUsserName(), escenario.vista.getPassword());
        } catch (UsuarioNoExisteException | PasswordErroneaException e) {
            fail("No se pudo iniciar sesión para agregar pedido");
        }
        
        escenario.controlador.actionPerformed(eventNuevoPedido);
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
    public void testActionPerformed_NUEVOVIAJE() {
    	ActionEvent eventNuevoViaje= new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.NUEVO_VIAJE);
		Pedido pedido2 =  new Pedido(escenario.empresa.getClientes().get("Eloncito"), escenario.vista.getCantidadPax(), escenario.vista.isPedidoConMascota(), escenario.vista.isPedidoConBaul(), escenario.vista.getCantKm(), escenario.vista.getTipoZona());
		try {
			escenario.empresa.agregarPedido(pedido2);
		} catch (Exception e) {
		}	
		escenario.vista.setPedidoSeleccionado(pedido2);
		escenario.controlador.actionPerformed(eventNuevoViaje);
	    Cliente cliente = escenario.empresa.getClientes().get("Eloncito");
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
    public void testActionPerformed_NUEVOCHOFER() {
        ActionEvent eventNuevoChofer = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.NUEVO_CHOFER);
        escenario.controlador.actionPerformed(eventNuevoChofer);
        
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
    public void testActionPerformed_NUEVOVEHICULO() {
        ActionEvent eventNuevoVehiculo = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.NUEVO_VEHICULO);
        escenario.controlador.actionPerformed(eventNuevoVehiculo);
        
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

}
