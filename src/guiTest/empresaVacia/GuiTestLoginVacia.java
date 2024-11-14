package guiTest.empresaVacia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import guiTest.TestUtil;
import integracionTest.OptionPaneMock;
import modeloNegocio.Empresa;
import static util.Constantes.*;
import util.Mensajes;
import vista.Ventana;

public class GuiTestLoginVacia {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	OptionPaneMock optionPane;
	
	public GuiTestLoginVacia() {
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
	}
	
	@Test
	public void testLoginUsuarioInexistente()
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
	public void testLogAdmin()
	{
		robot.delay(TestUtil.getDelay());
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), NOMBRE_USUARIO);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(),PASSWORD);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), LOGIN);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("admin", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("admin", robot);
		TestUtil.clickComponent(aceptarLogin, robot);
		
		robot.delay(TestUtil.getDelay());
		JPanel panelAdmin = (JPanel) TestUtil.getComponentForName((Ventana)controlador.getVista(), PANEL_ADMINISTRADOR);
		assertTrue("Deberia haber accedido al panel de administrador", panelAdmin != null);
	}
	
}
