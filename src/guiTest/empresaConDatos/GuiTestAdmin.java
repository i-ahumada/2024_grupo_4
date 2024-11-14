package guiTest.empresaConDatos;

import static org.junit.Assert.*;
import static util.Constantes.*;
import static util.Mensajes.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import guiTest.TestUtil;
import integracionTest.OptionPaneMock;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class GuiTestAdmin {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	OptionPaneMock optionPane;
	
	public GuiTestAdmin() {
		try {
			this.robot  = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		this.optionPane = new OptionPaneMock();
	}
	
	@Before
	public void setUp() throws Exception
	{
		controlador = new Controlador();
		empresa = Empresa.getInstance();
		controlador.getVista().setOptionPane(optionPane);
		empresa.agregarCliente("Mandarina","12345678","Mr Mandarina");
		Cliente cliente1 = empresa.getClientes().get("Mandarina");
		Chofer chofer1 = new ChoferTemporario("1000","Fangio");
		Vehiculo vehiculo1 = new Combi("JJ777JJ",9,false);
		empresa.agregarChofer(chofer1);
		empresa.agregarVehiculo(vehiculo1);
		empresa.setUsuarioLogeado(cliente1);
		Pedido pedido1;
		
		for(int i=0;i<3;i++)
		{
			pedido1  = new Pedido(cliente1, 4, false, true, 50*i, ZONA_PELIGROSA);
			empresa.agregarPedido(pedido1);
			empresa.crearViaje(pedido1, chofer1, vehiculo1);
			empresa.pagarYFinalizarViaje(4);
		}
		pedido1  = new Pedido(cliente1, 4, false, true, 50, ZONA_PELIGROSA);
		empresa.agregarPedido(pedido1);
		empresa.logout();
		
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), PASSWORD);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), LOGIN);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("admin", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("admin", robot);
		TestUtil.clickComponent(aceptarLogin, robot);
	}

	@After
	public void tearDown()
	{
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
		empresa.setClientes(new HashMap<String,Cliente>());
		empresa.setChoferes(new HashMap<String,Chofer>());
		empresa.setChoferesDesocupados(new ArrayList<Chofer>());
		empresa.setVehiculos(new HashMap<String,Vehiculo>());
		empresa.setVehiculosDesocupados(new ArrayList<Vehiculo>());
		empresa.setPedidos(new HashMap<Cliente,Pedido>());
		empresa.setViajesTerminados(new ArrayList<Viaje>());
	}
	
	@Test
	public void testNuevoChoferTemporario() {
	    robot.delay(TestUtil.getDelay());
	    JTextField dniChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.DNI_CHOFER);
	    JTextField nombreChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_CHOFER);
	    JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
	    JRadioButton temporario = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.TEMPORARIO);
	    JList listaChoferes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_CHOFERES_TOTALES);    
	    boolean encontrado = false;
	    
	    TestUtil.clickComponent(temporario, robot);
	    TestUtil.clickComponent(nombreChofer, robot);
	    TestUtil.tipeaTexto("Colapinto", robot);
	    TestUtil.clickComponent(dniChofer, robot);
	    TestUtil.tipeaTexto("23456", robot);
	    TestUtil.clickComponent(aceptarChofer, robot);
	    
	    for (int i = 0; i < listaChoferes.getModel().getSize() && !encontrado; i++) {
	        Chofer choferActual = (Chofer) listaChoferes.getModel().getElementAt(i);
	        if (choferActual.getDni().equals("23456")) {
	            encontrado = true;
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("El JList de choferes deberia contener al nuevo chofer", encontrado),
	        new SimpleEntry<>("El JTextField dni chofer deberia estar vacio", dniChofer.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField nombre de chofer deberia estar vacio", nombreChofer.getText().isEmpty())
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testNuevoChoferPermanente() {
	    robot.delay(TestUtil.getDelay());
	    JTextField dniChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.DNI_CHOFER);
	    JTextField nombreChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_CHOFER);
	    JTextField cantHijos = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_CANT_HIJOS);
	    JTextField anioIngreso = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_ANIO);
	    JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
	    JRadioButton permanente = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PERMANENTE);
	    JList listaChoferes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_CHOFERES_TOTALES);
	    boolean encontrado = false;
	    
	    TestUtil.clickComponent(permanente, robot);
	    TestUtil.clickComponent(nombreChofer, robot);
	    TestUtil.tipeaTexto("Hamilton", robot);
	    TestUtil.clickComponent(dniChofer, robot);
	    TestUtil.tipeaTexto("18872", robot);
	    TestUtil.clickComponent(anioIngreso, robot);
	    TestUtil.tipeaTexto("2024", robot);
	    TestUtil.clickComponent(cantHijos, robot);
	    TestUtil.tipeaTexto("1", robot);
	    TestUtil.clickComponent(aceptarChofer, robot);
	    
	    for (int i = 0; i < listaChoferes.getModel().getSize() && !encontrado; i++) {
	        Chofer choferActual = (Chofer) listaChoferes.getModel().getElementAt(i);
	        if (choferActual.getDni().equals("18872")) {
	            encontrado = true;
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("El JList de choferes deberia contener al nuevo chofer", encontrado),
	        new SimpleEntry<>("El JTextField dni chofer deberia estar vacio", dniChofer.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField nombre de chofer deberia estar vacio", nombreChofer.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField cantidad de hijos de chofer deberia estar vacio", cantHijos.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField anio ingreso de chofer deberia estar vacio", anioIngreso.getText().isEmpty())
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testNuevoVehiculo() {
	    robot.delay(TestUtil.getDelay());
	    JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
	    JTextField cantPlazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
	    JRadioButton combi = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.COMBI);
	    JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
	    JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
	    JList listaVehiculos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_VEHICULOS_TOTALES);
	    boolean encontrado = false;
	    
	    TestUtil.clickComponent(combi, robot);
	    TestUtil.clickComponent(mascota, robot);
	    TestUtil.clickComponent(patente, robot);
	    TestUtil.tipeaTexto("PP888PP", robot);
	    TestUtil.clickComponent(cantPlazas, robot);
	    TestUtil.tipeaTexto("7", robot);
	    TestUtil.clickComponent(aceptarVehiculo, robot);
	    

	    for (int i = 0; i < listaVehiculos.getModel().getSize() && !encontrado; i++) {
	        Vehiculo vehiculoActual = (Vehiculo) listaVehiculos.getModel().getElementAt(i);
	        if (vehiculoActual.getPatente().equals("PP888PP")) {
	            encontrado = true;
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("El JList de vehiculos deberia contener al nuevo vehiculo", encontrado),
	        new SimpleEntry<>("El JTextField patente de vehiculo deberia estar vacio", patente.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField cantidad de plazas vehiculo deberia estar vacio", cantPlazas.getText().isEmpty())
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test 
	public void testNuevoChoferRepetido()
	{
		robot.delay(TestUtil.getDelay());
		JTextField dniChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), DNI_CHOFER);
		JTextField nombreChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), NOMBRE_CHOFER);
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), NUEVO_CHOFER);
		JRadioButton temporario = (JRadioButton)  TestUtil.getComponentForName((Ventana)controlador.getVista(),TEMPORARIO);
		
		TestUtil.clickComponent(temporario, robot);
		TestUtil.clickComponent(nombreChofer, robot);
		TestUtil.tipeaTexto("Verstappen", robot);
		TestUtil.clickComponent(dniChofer, robot);
		TestUtil.tipeaTexto("1000", robot);
		TestUtil.clickComponent(aceptarChofer, robot);
		
	
		assertEquals("Mensaje incorrecto, deberia mostrar "+ CHOFER_YA_REGISTRADO.getValor(), CHOFER_YA_REGISTRADO.getValor(), optionPane.getMensaje());
	}
	
	@Test 
	public void testNuevoVehiculoRepetido()
	{
		robot.delay(TestUtil.getDelay());
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
		JTextField cantPlazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JRadioButton combi = (JRadioButton)  TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.COMBI);
		JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);

		
		TestUtil.clickComponent(combi, robot);
		TestUtil.clickComponent(mascota, robot);
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("JJ777JJ", robot);
		TestUtil.clickComponent(cantPlazas, robot);
		TestUtil.tipeaTexto("7", robot);
		TestUtil.clickComponent(aceptarVehiculo, robot);
		
		assertEquals("Mensaje incorrecto, deberia mostrar "+Mensajes.VEHICULO_YA_REGISTRADO.getValor(), Mensajes.VEHICULO_YA_REGISTRADO.getValor(), optionPane.getMensaje());
	}
	
	@Test
	public void testNuevoViaje() {
	    JList listaVehiculosDisp = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
	    JList listaChoferesDisp = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
	    JList listaPedidosPendientes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
	    JButton nuevoViaje = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VIAJE);
	    
	    int cantidad = empresa.getViajesIniciados().size();
	    TestUtil.seleccionarElementoJList(listaPedidosPendientes, 0, robot);
	    TestUtil.seleccionarElementoJList(listaChoferesDisp, 0, robot);
	    TestUtil.seleccionarElementoJList(listaVehiculosDisp, 0, robot);
	    TestUtil.clickComponent(nuevoViaje, robot);
	    boolean encontradoChofer = false;
	    boolean encontradoPedido = false;
	    
	    for (int i = 0; i < listaChoferesDisp.getModel().getSize(); i++) {
	        Chofer choferActual = (Chofer) listaChoferesDisp.getModel().getElementAt(i);
	        if (choferActual.getDni().equals("1000")) {
	            encontradoChofer = true;
	            break;
	        }
	    }
	    
	    for (int i = 0; i < listaPedidosPendientes.getModel().getSize(); i++) {
	        Pedido pedido = (Pedido) listaPedidosPendientes.getModel().getElementAt(i);
	        if (pedido.getCliente().getNombreUsuario().equals("Mandarina")) {
	            encontradoPedido = true;
	            break;
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Se deberia haber borrado el chofer de la lista de choferes disponibles", !encontradoChofer),
	        new SimpleEntry<>("Se deberia haber borrado el pedido de la lista de pedidos pendientes", !encontradoPedido),
	        new SimpleEntry<>("El JButton nuevo viaje deberia estar deshabilitado", !nuevoViaje.isEnabled()),
	        new SimpleEntry<>("Se deberia haber vaciado la lista de vehiculos disponibles", listaVehiculosDisp.getModel().getSize() == 0)
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testListadoChoferSeleccionado() {
	    JList listaChoferes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_CHOFERES_TOTALES);
	    JList viajesChofer = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VIAJES_DE_CHOFER);
	    JTextField calificacionChofer = (JTextField)  TestUtil.getComponentForName((Ventana)controlador.getVista(), CALIFICACION_CHOFER);
	    JTextField sueldoChofer = (JTextField)  TestUtil.getComponentForName((Ventana)controlador.getVista(), SUELDO_DE_CHOFER);
	    TestUtil.seleccionarElementoJList(listaChoferes, 0, robot);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = new ArrayList<>();
	    for (int i = 0; i < viajesChofer.getModel().getSize(); i++) {
	        Viaje viaje = (Viaje) viajesChofer.getModel().getElementAt(i);
	        Pedido pedido = viaje.getPedido();
	        
	        aserciones.add(new SimpleEntry<>("No coinciden los pasajeros del viaje terminado", pedido.getCantidadPasajeros() == 4));
	        aserciones.add(new SimpleEntry<>("No coinciden los km del viaje terminado", pedido.getKm() == 50 * i));
	        aserciones.add(new SimpleEntry<>("No coinciden la mascota del viaje terminado", !pedido.isMascota()));
	        aserciones.add(new SimpleEntry<>("No coinciden el baul del viaje terminado", pedido.isBaul()));
	        aserciones.add(new SimpleEntry<>("No coinciden la zona del viaje terminado", ZONA_PELIGROSA.equals(pedido.getZona())));
	        aserciones.add(new SimpleEntry<>("El textfield sueldo de chofer debería ser distinto de vacío", !sueldoChofer.getText().isEmpty()));
	        aserciones.add(new SimpleEntry<>("El textfield calificacion de chofer debería ser distinto de vacío", !calificacionChofer.getText().isEmpty()));
	    }
	    
	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testListadoClientes() {
	    JList listaClientes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTADO_DE_CLIENTES);
	    Cliente cliente = (Cliente) listaClientes.getModel().getElementAt(0);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Cantidad incorrecta de clientes totales", listaClientes.getModel().getSize() == 1),
	        new SimpleEntry<>("Cliente incorrecto", "Mandarina".equals(cliente.getNombreUsuario()))
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testListadoVehiculos() {
	    JList listaVehiculos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VEHICULOS_TOTALES);
	    Vehiculo vehiculo = (Vehiculo) listaVehiculos.getModel().getElementAt(0);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Cantidad incorrecta de vehiculos totales", listaVehiculos.getModel().getSize() == 1),
	        new SimpleEntry<>("Vehiculo incorrecto", "JJ777JJ".equals(vehiculo.getPatente()))
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testListadoChoferes() {
	    JList listaChoferes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_CHOFERES_TOTALES);
	    Chofer chofer = (Chofer) listaChoferes.getModel().getElementAt(0);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Cantidad incorrecta de choferes totales", listaChoferes.getModel().getSize() == 1),
	        new SimpleEntry<>("Chofer incorrecto", "1000".equals(chofer.getDni()))
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testListadoChoferesLibres() {
	    JList listaChoferesLibres = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_CHOFERES_LIBRES);
	    Chofer chofer = (Chofer) listaChoferesLibres.getModel().getElementAt(0);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Cantidad incorrecta de choferes libres", listaChoferesLibres.getModel().getSize() == 1),
	        new SimpleEntry<>("Chofer libre incorrecto", "1000".equals(chofer.getDni()))
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testListadoVehiculosLibres() {
	    JList listaPedidos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_PEDIDOS_PENDIENTES);
	    JList listaVehiculosLibres = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VEHICULOS_DISPONIBLES);
	    
	    TestUtil.seleccionarElementoJList(listaPedidos, 0, robot);
	    Vehiculo vehiculo = (Vehiculo) listaVehiculosLibres.getModel().getElementAt(0);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Cantidad incorrecta de vehiculos libres", listaVehiculosLibres.getModel().getSize() == 1),
	        new SimpleEntry<>("Vehiculo incorrecto", "JJ777JJ".equals(vehiculo.getPatente()))
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testListadoPedidosPendientes() {
	    JList listaPedidos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_PEDIDOS_PENDIENTES);
	    Pedido pedido = (Pedido) listaPedidos.getModel().getElementAt(0);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Cantidad incorrecta de pedidos totales", listaPedidos.getModel().getSize() == 1),
	        new SimpleEntry<>("Pedido incorrecto", "Mandarina".equals(pedido.getCliente().getNombreUsuario()))
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testListadoViajesTerminados() {
	    JList listaViajesTerminados = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VIAJES_HISTORICOS);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = new ArrayList<>();
	    for (int i = 0; i < listaViajesTerminados.getModel().getSize(); i++) {
	        Viaje viaje = (Viaje) listaViajesTerminados.getModel().getElementAt(i);
	        Pedido pedido = viaje.getPedido();
	        
	        aserciones.add(new SimpleEntry<>("No coinciden los pasajeros del viaje terminado", pedido.getCantidadPasajeros() == 4));
	        aserciones.add(new SimpleEntry<>("No coinciden los km del viaje terminado", pedido.getKm() == 50 * i));
	        aserciones.add(new SimpleEntry<>("No coinciden la mascota del viaje terminado", !pedido.isMascota()));
	        aserciones.add(new SimpleEntry<>("No coinciden el baul del viaje terminado", pedido.isBaul()));
	        aserciones.add(new SimpleEntry<>("No coinciden la zona del viaje terminado", ZONA_PELIGROSA.equals(pedido.getZona())));
	    }
	    
	    TestUtil.reportarErrores(aserciones);
	}

}
