package guiTest.enabledDisabled;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import guiTest.TestUtil;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;

public class GuiTestClienteEnabledDisabled {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	
	public GuiTestClienteEnabledDisabled() {
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
		empresa.agregarCliente("agustin51","12345678","Agustin Echeverria");
	
		JButton login = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LOGIN);
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PASSWORD);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("agustin51", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(login, robot);
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
	public void testClienteNuevoPedidoSoloCantPax() {
		robot.delay(TestUtil.getDelay());
		
		JButton nuevoPedido = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField pasajeros = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_PAX);
		
		TestUtil.clickComponent(pasajeros, robot);
		TestUtil.tipeaTexto("4", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton nuevo pedido deberia estar deshabilidato",nuevoPedido.isEnabled());
	}
	
	@Test
	public void testClienteNuevoPedidoSoloKM() {
		robot.delay(TestUtil.getDelay());
		
		JButton nuevoPedido = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField km = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_KM);
		
		TestUtil.clickComponent(km, robot);
		TestUtil.tipeaTexto("4", robot);
		
		robot.delay(TestUtil.getDelay());
		
		assertFalse("El boton nuevo pedido deberia estar deshabilidato",nuevoPedido.isEnabled());
	}
	
	@Test
	public void testClienteNuevoPedidoLlenoCorrectos() {
		robot.delay(TestUtil.getDelay());
		
		JButton nuevoPedido = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField km = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_KM);
		JTextField pasajeros = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_PAX);
		
		TestUtil.clickComponent(km, robot);
		TestUtil.tipeaTexto("4", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(pasajeros, robot);
		TestUtil.tipeaTexto("4", robot);
		
		robot.delay(TestUtil.getDelay());
		assertTrue("El boton nuevo pedido deberia estar habilidato",nuevoPedido.isEnabled());
	}
	
	@Test
	public void testClienteNuevoPedidoLlenoPaxMenorA1() {
		robot.delay(TestUtil.getDelay());
		
		JButton nuevoPedido = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField km = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_KM);
		JTextField pasajeros = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_PAX);
		
		TestUtil.clickComponent(km, robot);
		TestUtil.tipeaTexto("4", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(pasajeros, robot);
		TestUtil.tipeaTexto("0", robot);
		
		robot.delay(TestUtil.getDelay());
		assertFalse("El boton nuevo pedido deberia estar deshabilidato",nuevoPedido.isEnabled());
	}
	
	@Test
	public void testClienteNuevoPedidoLlenoPaxMayorA10() {
		robot.delay(TestUtil.getDelay());
		
		JButton nuevoPedido = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField km = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_KM);
		JTextField pasajeros = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_PAX);
		
		TestUtil.clickComponent(km, robot);
		TestUtil.tipeaTexto("4", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(pasajeros, robot);
		TestUtil.tipeaTexto("11", robot);
		
		robot.delay(TestUtil.getDelay());
		assertFalse("El boton nuevo pedido deberia estar deshabilidato",nuevoPedido.isEnabled());
	}
	
	@Test
	public void testClienteNuevoPedidoLlenoKmMenorA0() {
		robot.delay(TestUtil.getDelay());
		
		JButton nuevoPedido = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField km = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_KM);
		JTextField pasajeros = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_PAX);
		
		TestUtil.clickComponent(km, robot);
		TestUtil.tipeaTexto("-1", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(pasajeros, robot);
		TestUtil.tipeaTexto("4", robot);
		
		robot.delay(TestUtil.getDelay());
		assertFalse("El boton nuevo pedido deberia estar deshabilidato",nuevoPedido.isEnabled());
	}

	@Test
	public void testClienteCalificarMenorA0() {
		robot.delay(TestUtil.getDelay());
		JButton calificarPagar = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField calificacion = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
		calificacion.setEnabled(true);
		TestUtil.clickComponent(calificacion, robot);
		TestUtil.tipeaTexto("4", robot);
		assertFalse("El boton calificar y pagar deberia estar deshabilidato",calificarPagar.isEnabled());
	}
	@Test
	public void testClienteCalificarMayorA5() {
		robot.delay(TestUtil.getDelay());
		JButton calificarPagar = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField calificacion = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
		calificacion.setEnabled(true);
		TestUtil.clickComponent(calificacion, robot);
		TestUtil.tipeaTexto("4", robot);
		assertFalse("El boton calificar y pagar deberia estar deshabilidato",calificarPagar.isEnabled());
	}
	
	@Test
	public void testClienteCalificarMayorValido() {
		robot.delay(TestUtil.getDelay());
		JButton calificarPagar = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField calificacion = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
		calificacion.setEnabled(true);
		TestUtil.clickComponent(calificacion, robot);
		TestUtil.tipeaTexto("4", robot);
		assertFalse("El boton calificar y pagar deberia estar deshabilidato",calificarPagar.isEnabled());
	}
	

}
