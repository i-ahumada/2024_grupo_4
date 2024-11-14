package guiTest.empresaVacia;

import static org.junit.Assert.*;
import static util.Constantes.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
import util.Mensajes;
import vista.Ventana;

public class GuiTestClienteVacia {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	OptionPaneMock optionPane;
	
	public GuiTestClienteVacia() {
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
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LOGIN);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("Mandarina", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		TestUtil.clickComponent(aceptarLogin, robot);
	}
	
	@After
	public void tearDown()
	{
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
		empresa.setClientes(new HashMap<String,Cliente>());
	}
	
	@Test
	public void testNuevoPedidoNoSatisfacible()
	{
		robot.delay(TestUtil.getDelay());
		JButton nuevoPedido = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
		JTextField pasajeros = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_PAX);
		JTextField km = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_KM);
		JRadioButton zonaPeligrosa = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.ZONA_PELIGROSA);
		JCheckBox baul = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_BAUL);
		JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_MASCOTA);

		
		TestUtil.clickComponent(mascota, robot);
		TestUtil.clickComponent(baul, robot);
		TestUtil.clickComponent(zonaPeligrosa, robot);
		TestUtil.clickComponent(pasajeros, robot);
		TestUtil.tipeaTexto("4", robot);
		TestUtil.clickComponent(km, robot);
		TestUtil.tipeaTexto("4", robot);
		TestUtil.clickComponent(nuevoPedido, robot);
		
		robot.delay(TestUtil.getDelay());
		assertEquals("Mensaje incorrecto, deberia mostrar "+Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), optionPane.getMensaje());
    
	}
	
	
	@Test
	public void testClienteCierraSesion()
	{	robot.delay(TestUtil.getDelay());
		JButton cerrarSesion = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);
		TestUtil.clickComponent(cerrarSesion, robot);
		JPanel panelLogin = (JPanel) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PANEL_LOGIN);
		assertTrue("Deberia haber vuelto al panel de login", panelLogin != null);
	}

	@Test
	public void testPanelClienteJTextFieldsvacios()
	{
		robot.delay(TestUtil.getDelay());
		JTextField km = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CANT_KM);
		JTextField calificacionViaje = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CALIFICACION_DE_VIAJE);
		JTextField cantPax = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), CANT_PAX);
		JButton cerrarSesion = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);

		
		TestUtil.clickComponent(km, robot);
		TestUtil.tipeaTexto("km", robot);
		TestUtil.clickComponent(cantPax, robot);
		TestUtil.tipeaTexto("cantPax", robot);
		TestUtil.clickComponent(cerrarSesion, robot);
	
		robot.delay(TestUtil.getDelay());
		robot.delay(TestUtil.getDelay());
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LOGIN);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("Mandarina", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		TestUtil.clickComponent(aceptarLogin, robot);
		
		 List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
				 new SimpleEntry<>("El JTextField km deberia estar vacio", km.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField calificacionViaje deberia estar vacio", calificacionViaje.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField cantPax deberia estar vacio", cantPax.getText().isEmpty())
			);
		 TestUtil.reportarErrores(aserciones);
		
	}

}
