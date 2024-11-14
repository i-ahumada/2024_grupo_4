package guiTest.enabledDisabled;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controlador.Controlador;
import guiTest.TestUtil;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;

public class GuiTestAdminEnabledDisabled {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	
	public GuiTestAdminEnabledDisabled() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() throws Exception{
		controlador = new Controlador();
		empresa = Empresa.getInstance();
		Vehiculo vehiculo = new Auto("JWL 201",3,false);
		ChoferTemporario chofer = new ChoferTemporario("12304590","Fangio");
		empresa.agregarCliente("sofiaV","qwerty","Sofia Venza");
		Pedido pedido = new Pedido(empresa.getClientes().get("sofiaV"),2,false,true,5,Constantes.ZONA_STANDARD);
		empresa.agregarVehiculo(vehiculo);
		empresa.agregarChofer(chofer);
		empresa.agregarPedido(pedido);
		
		robot.delay(TestUtil.getDelay());
		
		
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LOGIN);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PASSWORD);
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_USUARIO);
		
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("admin", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("admin", robot);
		
		TestUtil.clickComponent(aceptarLogin, robot);
	}
	
	@After
	public void tearDown() throws Exception{
		
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
		
		empresa.setChoferes(new HashMap<String,Chofer>());
		empresa.setChoferesDesocupados(new ArrayList<Chofer>());
		empresa.setClientes(new HashMap<String, Cliente>());
		empresa.setPedidos(new HashMap<Cliente, Pedido>());
		empresa.setUsuarioLogeado(null);
		empresa.setVehiculos(new HashMap<String, Vehiculo>());
		empresa.setVehiculosDesocupados(new ArrayList<Vehiculo>());
		empresa.setViajesIniciados(new HashMap<Cliente, Viaje>());
		empresa.setViajesTerminados(new ArrayList<Viaje>());
		empresa = null;
	}
	
	@Test
	public void testAdminNuevoChoferSoloDNI() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
		JTextField dni = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.DNI_CHOFER);
		
		TestUtil.clickComponent(dni, robot);
		TestUtil.tipeaTexto("38461210", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarChofer deberia estar deshabilitado", aceptarChofer.isEnabled());
	}
	
	@Test
	public void testAdminNuevoChoferSoloNombre() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_CHOFER);
		
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Franco Colapinto", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarChofer deberia estar deshabilitado", aceptarChofer.isEnabled());
	}
	
	@Test
	public void testAdminNuevoChoferPermanenteSoloHijos() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
		JTextField cantidadHijos = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JRadioButton permanente = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PERMANENTE);
		
		TestUtil.clickComponent(permanente, robot);
		
		TestUtil.clickComponent(cantidadHijos, robot);
		TestUtil.tipeaTexto("2", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarChofer deberia estar deshabilitado", aceptarChofer.isEnabled());
	}
	
	@Test
	public void testAdminNuevoChoferPermanenteSoloIngreso() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
		JTextField ingreso = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_ANIO);
		JRadioButton permanente = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PERMANENTE);
		
		TestUtil.clickComponent(permanente, robot);
		
		TestUtil.clickComponent(ingreso, robot);
		TestUtil.tipeaTexto("2000", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarChofer deberia estar deshabilitado", aceptarChofer.isEnabled());
	}
	
	@Test
	public void testAdminNuevoChoferPermanenteLleno() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
		JTextField ingreso = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_ANIO);
		JRadioButton permanente = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PERMANENTE);
		JTextField cantidadHijos = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JTextField dni = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.DNI_CHOFER);
		
		TestUtil.clickComponent(permanente, robot);
		
		TestUtil.clickComponent(ingreso, robot);
		TestUtil.tipeaTexto("2000", robot);
		
		robot.delay(TestUtil.getDelay());
		
		TestUtil.clickComponent(cantidadHijos, robot);
		TestUtil.tipeaTexto("2", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Franco Colapinto", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(dni, robot);
		TestUtil.tipeaTexto("38410210", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertTrue("El boton de aceptarChofer deberia estar habilitado", aceptarChofer.isEnabled());
	}
	
	@Test
	public void testAdminnuevowChoferPermanenteLlenoHijosConCaracteres() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
		JTextField ingreso = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_ANIO);
		JRadioButton permanente = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PERMANENTE);
		JTextField cantidadHijos = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JTextField dni = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.DNI_CHOFER);
		
		TestUtil.clickComponent(permanente, robot);
		
		TestUtil.clickComponent(ingreso, robot);
		TestUtil.tipeaTexto("2000", robot);
		
		robot.delay(TestUtil.getDelay());
		
		TestUtil.clickComponent(cantidadHijos, robot);
		TestUtil.tipeaTexto("a", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Franco Colapinto", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(dni, robot);
		TestUtil.tipeaTexto("38410210", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarChofer deberia estar deshabilitado", aceptarChofer.isEnabled());
	}
	
	@Test
	public void testAdminNuevoChoferPermanenteLlenoHijosNegativos() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
		JTextField ingreso = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_ANIO);
		JRadioButton permanente = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PERMANENTE);
		JTextField cantidadHijos = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JTextField dni = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.DNI_CHOFER);
		
		TestUtil.clickComponent(permanente, robot);
		
		TestUtil.clickComponent(ingreso, robot);
		TestUtil.tipeaTexto("2000", robot);
		
		robot.delay(TestUtil.getDelay());
		
		TestUtil.clickComponent(cantidadHijos, robot);
		TestUtil.tipeaTexto("-1", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Franco Colapinto", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(dni, robot);
		TestUtil.tipeaTexto("38410210", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarChofer deberia estar deshabilitado", aceptarChofer.isEnabled());
	}
	
	@Test
	public void testAdminNuevoChoferPermanenteLlenoIngresoFueraDelRango() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
		JTextField ingreso = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_ANIO);
		JRadioButton permanente = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PERMANENTE);
		JTextField cantidadHijos = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CH_CANT_HIJOS);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JTextField dni = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.DNI_CHOFER);
		
		TestUtil.clickComponent(permanente, robot);
		
		TestUtil.clickComponent(ingreso, robot);
		TestUtil.tipeaTexto("1000", robot);
		
		robot.delay(TestUtil.getDelay());
		
		TestUtil.clickComponent(cantidadHijos, robot);
		TestUtil.tipeaTexto("2", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Franco Colapinto", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(dni, robot);
		TestUtil.tipeaTexto("38410210", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarChofer deberia estar deshabilitado", aceptarChofer.isEnabled());
	}
	
	@Test
	public void testAdminNuevoChoferTemporarioLleno() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarChofer = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_CHOFER);
		JTextField dni = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.DNI_CHOFER);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_CHOFER);
		JRadioButton temporario = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.TEMPORARIO);
		
		
		TestUtil.clickComponent(dni, robot);
		TestUtil.tipeaTexto("38461210", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(temporario, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Franco Colapinto", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertTrue("El boton de aceptarChofer deberia estar habilitado", aceptarChofer.isEnabled());
	}
	
	@Test
	public void testAdminNuevoVehiculoSoloPatente() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
		
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("JWZ 123", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevoAutoSoloBoton() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton auto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.AUTO);
		
		TestUtil.clickComponent(auto, robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevaMotoSoloBoton() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton moto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.MOTO);
		
		TestUtil.clickComponent(moto, robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevaCombiSoloBoton() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton combi = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.COMBI);
		
		TestUtil.clickComponent(combi, robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevaCombiSoloPlazas() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton combi = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.COMBI);
		JTextField plazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		
		TestUtil.clickComponent(combi, robot);
		TestUtil.clickComponent(plazas, robot);
		TestUtil.tipeaTexto("3", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevaCombiLlenoPlazasCorrecta() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton combi = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.COMBI);
		JTextField plazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
		
		TestUtil.clickComponent(combi, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("JWL 120", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(plazas, robot);
		TestUtil.tipeaTexto("8", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertTrue("El boton de aceptarVehiculo deberia estar habilitado", aceptarVehiculo.isEnabled());
	}
		
	@Test
	public void testAdminNuevaCombiLlenoPlazasMenorA5() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton combi = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.COMBI);
		JTextField plazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
		
		TestUtil.clickComponent(combi, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("JWL 120", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(mascota, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(plazas, robot);
		TestUtil.tipeaTexto("4", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevaCombiLlenoPlazasMayorA10() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton combi = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.COMBI);
		JTextField plazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
		
		TestUtil.clickComponent(combi, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("JWL 120", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(mascota, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(plazas, robot);
		TestUtil.tipeaTexto("11", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevaMotoLleno() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton moto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.MOTO);
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
		
		TestUtil.clickComponent(moto, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("AA123BB", robot);
		
		robot.delay(TestUtil.getDelay());

		assertTrue("El boton de aceptarVehiculo deberia estar habilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevoAutoSoloPlazas() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton auto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.AUTO);
		JTextField plazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		
		TestUtil.clickComponent(auto, robot);
		TestUtil.clickComponent(plazas, robot);
		TestUtil.tipeaTexto("3", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevoAutoLlenoPlazasCorrecta() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton auto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.AUTO);
		JTextField plazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
		
		TestUtil.clickComponent(auto, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("JWL 120", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(plazas, robot);
		TestUtil.tipeaTexto("3", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertTrue("El boton de aceptarVehiculo deberia estar habilitado", aceptarVehiculo.isEnabled());
	}
		
	@Test
	public void testAdminNuevoAutoLlenoPlazasMenorA1() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton auto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.AUTO);
		JTextField plazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
		
		TestUtil.clickComponent(auto, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("JWL 120", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(mascota, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(plazas, robot);
		TestUtil.tipeaTexto("0", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevoAutoLlenoPlazasMayorA4() {
		
		
		robot.delay(TestUtil.getDelay());
		
		JButton aceptarVehiculo = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VEHICULO);
		JRadioButton auto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.AUTO);
		JTextField plazas = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
		JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
		JTextField patente = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PATENTE);
		
		TestUtil.clickComponent(auto, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(patente, robot);
		TestUtil.tipeaTexto("JWL 120", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(mascota, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(plazas, robot);
		TestUtil.tipeaTexto("5", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton de aceptarVehiculo deberia estar deshabilitado", aceptarVehiculo.isEnabled());
	}
	
	@Test
	public void testAdminNuevoViajeSoloPedidosPendientes(){
		JList pedidos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
		robot.delay(TestUtil.getDelay());
		
		TestUtil.seleccionarElementoJList(pedidos, 0, robot);
		
		robot.delay(TestUtil.getDelay());
		JButton nuevoViaje = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VIAJE);
		
		assertFalse("El boton de nuevo viaje deberia estar deshabilitado", nuevoViaje.isEnabled());
	}
	
	@Test
	public void testAdminNuevoViajeSoloChoferesDisponibles(){
		JList choferes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
		robot.delay(TestUtil.getDelay());
		
		TestUtil.seleccionarElementoJList(choferes, 0, robot);
		
		robot.delay(TestUtil.getDelay());
		JButton nuevoViaje = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VIAJE);
		
		assertFalse("El boton de nuevo viaje deberia estar deshabilitado", nuevoViaje.isEnabled());
	}
	
	@Test
	public void testAdminNuevoViajeSoloPedidosYVehiculos(){
		JList pedidos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
		JList vehiculos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.seleccionarElementoJList(pedidos, 0, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.seleccionarElementoJList(vehiculos, 0, robot);
		
		JButton nuevoViaje = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VIAJE);
		
		robot.delay(TestUtil.getDelay());
		assertFalse("El boton de nuevo viaje deberia estar deshabilitado", nuevoViaje.isEnabled());
	}
	
	@Test
	public void testAdminNuevoViajeSoloPedidosYChofer(){
		JList pedidos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
		JList choferes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.seleccionarElementoJList(pedidos, 0, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.seleccionarElementoJList(choferes, 0, robot);
		
		JButton nuevoViaje = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VIAJE);
		
		robot.delay(TestUtil.getDelay());
		assertFalse("El boton de nuevo viaje deberia estar deshabilitado", nuevoViaje.isEnabled());
	}
	
	@Test
	public void testAdminNuevoViajeLleno(){
		JList pedidos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
		JList vehiculos = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
		JList choferes = (JList) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.seleccionarElementoJList(pedidos, 0, robot);
		
		robot.delay(TestUtil.getDelay());
		robot.delay(TestUtil.getDelay());
		robot.delay(TestUtil.getDelay());
		TestUtil.seleccionarElementoJList(vehiculos, 0, robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.seleccionarElementoJList(choferes, 0, robot);
		
		robot.delay(TestUtil.getDelay());
		JButton nuevoViaje = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_VIAJE);
		
		assertTrue("El boton de nuevo viaje deberia estar habilitado", nuevoViaje.isEnabled());
	}
	
}


