package guiTest.enabledDisabled;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import guiTest.TestUtil;
import vista.Ventana;
import util.Constantes;

public class GuiTestLoginEnabledDisabled {
	Robot robot;
	Controlador controlador;
	public GuiTestLoginEnabledDisabled() {
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
		
	}
	
	@After
	public void tearDown() throws Exception{
		
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
	}

	@Test
	public void testLoginSoloUsuario() {
		robot.delay(TestUtil.getDelay());
		
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_USUARIO);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LOGIN);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("esteban", robot);
		
		assertFalse("El boton de login deberia estar deshabilitado", aceptarLogin.isEnabled());
	}

	@Test
	public void testLoginSoloContra() {
		robot.delay(TestUtil.getDelay());
		
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PASSWORD);
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LOGIN);
		
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("qwerty", robot);
		
		assertFalse("El boton de login deberia estar deshabilitado", aceptarLogin.isEnabled());
	}
	
	@Test
	public void testLoginLleno() {
		robot.delay(TestUtil.getDelay());
		
	
		JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LOGIN);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PASSWORD);
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_USUARIO);
		
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("qwerty", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("esteban", robot);
		
		assertTrue("El boton de login deberia estar habilitado", aceptarLogin.isEnabled());
	}
	
	
}

