package guiTest.empresaConDatos;

import static org.junit.Assert.*;
import static util.Constantes.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import guiTest.TestUtil;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;
import integracionTest.OptionPaneMock;
import modeloDatos.Cliente;

public class GuiTestLogin {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	OptionPaneMock optionPane;
	
	public GuiTestLogin() {
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
	}

	@After
	public void tearDown()
	{
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
		empresa.setClientes(new HashMap<String,Cliente>());
	}
	
	@Test 
	public void testLogClienteExitoso()
	{
		robot.delay(TestUtil.getDelay());
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), PASSWORD);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), LOGIN);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("Mandarina", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		TestUtil.clickComponent(aceptarLogin, robot);
		
		JPanel panelCliente = (JPanel) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PANEL_CLIENTE);
		assertTrue("Deberia haberse cambiado a panel cliente", panelCliente != null);
		assertEquals("Nombre de usuario en panel incorrecto", "Mr Mandarina", ((TitledBorder) panelCliente.getBorder()).getTitle());
	}
	
	@Test 
	public void testLogUsuarioInexistente()
	{
		robot.delay(TestUtil.getDelay());
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), PASSWORD);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), LOGIN);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("Gian", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		TestUtil.clickComponent(aceptarLogin, robot);
		
		assertEquals("Mensaje incorrecto, deberia mostrar"+Mensajes.USUARIO_DESCONOCIDO.getValor(), Mensajes.USUARIO_DESCONOCIDO.getValor(), optionPane.getMensaje());
		
	}
	
	@Test 
	public void testLogContrasenaMal()
	{
		robot.delay(TestUtil.getDelay());
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), PASSWORD);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), LOGIN);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("Mandarina", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("password", robot);
		TestUtil.clickComponent(aceptarLogin, robot);
		
		assertEquals("Mensaje incorrecto, deberia mostrar"+Mensajes.PASS_ERRONEO.getValor(), Mensajes.PASS_ERRONEO.getValor(), optionPane.getMensaje());
	}
	
}
