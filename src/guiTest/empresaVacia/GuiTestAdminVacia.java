package guiTest.empresaVacia;

import static org.junit.Assert.*;
import static util.Constantes.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import controlador.Controlador;
import guiTest.TestUtil;
import integracionTest.OptionPaneMock;
import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;

public class GuiTestAdminVacia {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	OptionPaneMock optionPane;
	
	public GuiTestAdminVacia() {
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
	    int cantidad = empresa.getChoferes().size();
	    
	    TestUtil.clickComponent(temporario, robot);
	    TestUtil.clickComponent(nombreChofer, robot);
	    TestUtil.tipeaTexto("Colapinto", robot);
	    TestUtil.clickComponent(dniChofer, robot);
	    TestUtil.tipeaTexto("23456", robot);
	    TestUtil.clickComponent(aceptarChofer, robot);
	    
	    int cantidadActual = empresa.getChoferes().size();
	    boolean encontrado = false;
	    
	    for (int i = 0; i < listaChoferes.getModel().getSize() && !encontrado; i++) {
	        Chofer choferActual = (Chofer) listaChoferes.getModel().getElementAt(i);
	        if (choferActual.getDni().equals("23456")) {
	            encontrado = true; 
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Se deberia haber añadido un chofer", cantidadActual == cantidad+ 1),
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
	    int cantidad = empresa.getChoferes().size();
	    
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
	    
	    int cantidadActual = empresa.getChoferes().size();
	    boolean encontrado = false;
	    
	    for (int i = 0; i < listaChoferes.getModel().getSize() && !encontrado; i++) {
	        Chofer choferActual = (Chofer) listaChoferes.getModel().getElementAt(i);
	        if (choferActual.getDni().equals("18872")) {
	            encontrado = true; 
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Se deberia haber añadido un chofer", cantidadActual == cantidad + 1),
	        new SimpleEntry<>("El JList de choferes deberia contener al nuevo chofer", encontrado),
	        new SimpleEntry<>("El JTextField dni chofer deberia estar vacio", dniChofer.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField nombre de chofer deberia estar vacio", nombreChofer.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField cantidad de hijos de chofer deberia estar vacio", cantHijos.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField anio ingreso de chofer deberia estar vacio", anioIngreso.getText().isEmpty())
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testNuevoAuto() {
	    robot.delay(TestUtil.getDelay());
	    JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), PATENTE);
	    JTextField cantPlazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CANTIDAD_PLAZAS);
	    JRadioButton auto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), AUTO);
	    JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), CHECK_VEHICULO_ACEPTA_MASCOTA);
	    JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), NUEVO_VEHICULO);
	    JList listaVehiculos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VEHICULOS_TOTALES);
	    int cantidad = empresa.getVehiculos().size();
	    
	    TestUtil.clickComponent(auto, robot);
	    TestUtil.clickComponent(mascota, robot);
	    TestUtil.clickComponent(patente, robot);
	    TestUtil.tipeaTexto("PP888PP", robot);
	    TestUtil.clickComponent(cantPlazas, robot);
	    TestUtil.tipeaTexto("4", robot);
	    TestUtil.clickComponent(aceptarVehiculo, robot);
	    
	    int cantidadActual = empresa.getVehiculos().size();
	    boolean encontrado = false;
	    
	    for (int i = 0; i < listaVehiculos.getModel().getSize() && !encontrado; i++) {
	        Vehiculo vehiculoActual = (Vehiculo) listaVehiculos.getModel().getElementAt(i);
	        if (vehiculoActual.getPatente().equals("PP888PP")) {
	            encontrado = true; 
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Se deberia haber añadido un auto", cantidadActual == cantidad + 1),
	        new SimpleEntry<>("El JList de vehiculo deberia contener al nuevo auto", encontrado),
	        new SimpleEntry<>("El JTextField patente de vehiculo deberia estar vacio", patente.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField cantidad de plazas vehiculo deberia estar vacio", cantPlazas.getText().isEmpty())
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testNuevaCombi() {
	    robot.delay(TestUtil.getDelay());
	    JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), PATENTE);
	    JTextField cantPlazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CANTIDAD_PLAZAS);
	    JRadioButton combi = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), COMBI);
	    JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), CHECK_VEHICULO_ACEPTA_MASCOTA);
	    JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), NUEVO_VEHICULO);
	    JList listaVehiculos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VEHICULOS_TOTALES);
	    int cantidad = empresa.getVehiculos().size();
	    
	    TestUtil.clickComponent(combi, robot);
	    TestUtil.clickComponent(mascota, robot);
	    TestUtil.clickComponent(patente, robot);
	    TestUtil.tipeaTexto("PP888PP", robot);
	    TestUtil.clickComponent(cantPlazas, robot);
	    TestUtil.tipeaTexto("7", robot);
	    TestUtil.clickComponent(aceptarVehiculo, robot);
	    
	    int cantidadActual = empresa.getVehiculos().size();
	    boolean encontrado = false;
	    
	    for (int i = 0; i < listaVehiculos.getModel().getSize() && !encontrado; i++) {
	        Vehiculo vehiculoActual = (Vehiculo) listaVehiculos.getModel().getElementAt(i);
	        if (vehiculoActual.getPatente().equals("PP888PP")) {
	            encontrado = true; 
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Se deberia haber añadido una combi", cantidadActual == cantidad + 1),
	        new SimpleEntry<>("El JList de vehiculo deberia contener a la nueva combi", encontrado),
	        new SimpleEntry<>("El JTextField patente de vehiculo deberia estar vacio", patente.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField cantidad de plazas vehiculo deberia estar vacio", cantPlazas.getText().isEmpty())
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testNuevaMoto() {
	    robot.delay(TestUtil.getDelay());
	    JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), PATENTE);
	    JRadioButton moto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), MOTO);
	    JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), NUEVO_VEHICULO);
	    JList listaVehiculos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VEHICULOS_TOTALES);
	    int cantidad = empresa.getVehiculos().size();
	    
	    TestUtil.clickComponent(moto, robot);
	    TestUtil.clickComponent(patente, robot);
	    TestUtil.tipeaTexto("PP888PP", robot);
	    TestUtil.clickComponent(aceptarVehiculo, robot);
	    
	    int cantidadActual = empresa.getVehiculos().size();
	    boolean encontrado = false;
	    
	    for (int i = 0; i < listaVehiculos.getModel().getSize() && !encontrado; i++) {
	        Vehiculo vehiculoActual = (Vehiculo) listaVehiculos.getModel().getElementAt(i);
	        if (vehiculoActual.getPatente().equals("PP888PP")) {
	            encontrado = true; 
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("Se deberia haber añadido una moto", cantidadActual == cantidad + 1),
	        new SimpleEntry<>("El JList de vehiculo deberia contener a la nueva moto", encontrado),
	        new SimpleEntry<>("El JTextField patente de vehiculo deberia estar vacio", patente.getText().isEmpty())
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testListadoChoferSeleccionado() {
	    JList viajesChofer = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VIAJES_DE_CHOFER);
	    JTextField calificacionChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CALIFICACION_CHOFER);
	    JTextField sueldoChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), SUELDO_DE_CHOFER);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("No deberia mostrarse nada en listado de chofer", viajesChofer.getModel().getSize() == 0),
	        new SimpleEntry<>("No deberia mostrarse nada en calificacion de chofer", calificacionChofer.getText().isEmpty()),
	        new SimpleEntry<>("No deberia mostrarse nada en sueldo de chofer", sueldoChofer.getText().isEmpty())
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	
	@Test 
	public void testListadoClientes()
	{
		JList listaClientes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTADO_DE_CLIENTES);
		assertEquals("No deberia haber clientes", 0, listaClientes.getModel().getSize());
	}
	
	@Test 
	public void testListadoVehiculos()
	{
		JList listaVehiculos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VEHICULOS_TOTALES);
		assertEquals("No deberia haber vehiculos", 0, listaVehiculos.getModel().getSize());
	}
	
	@Test 
	public void testListadoChoferes()
	{
		JList listaChoferes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_CHOFERES_TOTALES);
		assertEquals("No deberia haber vehiculos", 0, listaChoferes.getModel().getSize());
	}
	
	@Test 
	public void testListadoChoferesLibres()
	{
		JList listaChoferesLibres = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_CHOFERES_LIBRES);
		assertEquals("No deberia haber choferes libres", 0, listaChoferesLibres.getModel().getSize());
	}
	
	@Test 
	public void testListadoVehiculosLibres()
	{
		JList listaVehiculosLibres = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VEHICULOS_DISPONIBLES);
		assertEquals("No deberia haber vehiculos libres", 0, listaVehiculosLibres.getModel().getSize());
	}
	
	@Test 
	public void testListadoPedidosPendientes()
	{
		JList listaPedidos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_PEDIDOS_PENDIENTES);
		assertEquals("No deberia haber pedidos pendientes", 0, listaPedidos.getModel().getSize());
	}
	
	@Test 
	public void testListadoViajesTerminados()
	{
		JList listaViajesTerminados = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), LISTA_VIAJES_HISTORICOS);
		assertEquals("No deberia haber viajes terminados", 0, listaViajesTerminados.getModel().getSize());
	}
	
	@Test
	public void testSueldosTotales()
	{
		JTextField sueldos = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), TOTAL_SUELDOS_A_PAGAR);
		assertTrue("Deberia mostrarse $0 en sueldos totales a pagar", sueldos.getText().equals("0,00"));
	}
	
	@Test
	public void testAdminCierraSesion()
	{
		JButton cerrarSesion = (JButton)  TestUtil.getComponentForName((Ventana)controlador.getVista(), CERRAR_SESION_ADMIN);
		TestUtil.clickComponent(cerrarSesion, robot);
		JPanel panelLogin = (JPanel) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PANEL_LOGIN);
		assertTrue("Deberia haber vuelto al panel de login", panelLogin != null);
	}
	
	@Test 
	public void testPanelAdminTextFieldsVacios()
	{
		robot.delay(TestUtil.getDelay());
		JButton cerrarSesion = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), CERRAR_SESION_ADMIN);
		
		robot.delay(TestUtil.getDelay());
		robot.delay(TestUtil.getDelay());
		JTextField sueldoChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), SUELDO_DE_CHOFER);
		JTextField calificacionChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CALIFICACION_CHOFER);
		JTextField sueldosTotales = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), TOTAL_SUELDOS_A_PAGAR);
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), PATENTE);
		JTextField dniChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), DNI_CHOFER);
		JTextField nombreChofer = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), NOMBRE_CHOFER);
		JTextField cantHijos = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CH_CANT_HIJOS);
		JTextField anioIngreso = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CH_ANIO);
		JTextField cantPlazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CANTIDAD_PLAZAS);
		
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("patente", robot);
		TestUtil.clickComponent(sueldoChofer, robot);
		TestUtil.tipeaTexto("dniChofer", robot);
		TestUtil.clickComponent(dniChofer, robot);
		TestUtil.tipeaTexto("cantHijos", robot);
		TestUtil.clickComponent(nombreChofer, robot);
		TestUtil.tipeaTexto("nombreChofer", robot);
		TestUtil.clickComponent(anioIngreso, robot);
		TestUtil.tipeaTexto("anioIngreso", robot);
		TestUtil.clickComponent(cantPlazas, robot);
		TestUtil.tipeaTexto("cantPlazas", robot);
		TestUtil.clickComponent(cerrarSesion, robot);
		robot.delay(TestUtil.getDelay());
		robot.delay(TestUtil.getDelay());
		
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(),PASSWORD);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), LOGIN);
		
		robot.delay(TestUtil.getDelay());
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("admin", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("admin", robot);
		TestUtil.clickComponent(aceptarLogin, robot);
		
		 List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
				 new SimpleEntry<>("El JTextField sueldoChofer deberia estar vacio", sueldoChofer.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField calificacionChofer deberia estar vacio", calificacionChofer.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField sueldosTotales deberia estar vacio", sueldosTotales.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField patente deberia estar vacio", patente.getText().equals("0,00")),
				 new SimpleEntry<>("El JTextField dniChofer deberia estar vacio", dniChofer.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField nombreCHofer deberia estar vacio", nombreChofer.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField cantHijos deberia estar vacio", cantHijos.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField anioIngreso deberia estar vacio", anioIngreso.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField cantPlazas deberia estar vacio", cantPlazas.getText().isEmpty())
			);
		 TestUtil.reportarErrores(aserciones);
	}
	
}
