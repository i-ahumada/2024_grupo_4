package guiTest.enabledDisabled;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import guiTest.TestUtil;
import util.Constantes;
import vista.Ventana;

public class GuiTestRegistroEnabledDisabled {
	Robot robot;
	Controlador controlador;
	
	public GuiTestRegistroEnabledDisabled() {
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
		robot.delay(TestUtil.getDelay());
		
		JButton registrar = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REGISTRAR);
		TestUtil.clickComponent(registrar,robot);
	}
	
	@After
	public void tearDown() throws Exception{
		
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
	}
	
	@Test
	public void testRegistroSoloUsuario() {
		
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_USSER_NAME);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);

		
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("esteban", robot);
		
		assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	}

	@Test
	public void testRegistroSoloContra() {
		
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_PASSWORD);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("qwerty", robot);
		
		assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	}
	
	@Test
	public void testRegistroSoloRepetirContra() {
		
		JTextField repPass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		
		TestUtil.clickComponent(repPass, robot);
		TestUtil.tipeaTexto("qwerty", robot);
		
		assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	}
	
	@Test
	public void testRegistroSoloNombre() {
		
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_REAL_NAME);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Esteban Gutierrez", robot);
		
		assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	}
	
	@Test
	public void testRegistroUsuarioYContra() {
		
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_USSER_NAME);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_PASSWORD);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);

		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("qwerty", robot);
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("esteban", robot);
		
		assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	}
	
	@Test
	public void testRegistroUsuarioContrayConfirmaContra() {
		
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_USSER_NAME);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_PASSWORD);
		JTextField repPass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);

		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("qwerty", robot);
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("esteban", robot);
		TestUtil.clickComponent(repPass, robot);
		TestUtil.tipeaTexto("qwerty", robot);
		
		assertFalse("El boton de registro deberia estar deshabilitado", aceptarReg.isEnabled());
	}
	
	@Test
	public void testRegistroLleno() {
		
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_USSER_NAME);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_PASSWORD);
		JTextField repPass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_REAL_NAME);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Esteban Gutierrez", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("esteban", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("qwerty", robot);
		
		robot.delay(TestUtil.getDelay());
		TestUtil.clickComponent(repPass, robot);
		TestUtil.tipeaTexto("qwerty", robot);
		
		assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	}
	
}

