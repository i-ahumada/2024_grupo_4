package guiTest.empresaVacia;

import static org.junit.Assert.*;
import static util.Constantes.CALIFICACION_DE_VIAJE;
import static util.Constantes.CANT_KM;
import static util.Constantes.CANT_PAX;

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
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class GuiTestRegistroVacia {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	OptionPaneMock optionPane;
	
	public GuiTestRegistroVacia() {
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
		JButton registrar = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REGISTRAR);
		TestUtil.clickComponent(registrar,robot);
	}

	@Test
	public void testRegistroExitoso()
	{
		robot.delay(TestUtil.getDelay());
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_USSER_NAME);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_REAL_NAME);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_PASSWORD);
		JTextField repPass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("Gian", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		TestUtil.clickComponent(repPass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Gian Magliotti", robot);
		TestUtil.clickComponent(nombre, robot);
		TestUtil.clickComponent(aceptarReg, robot);
		
		robot.delay(TestUtil.getDelay());
		JPanel panelLogin = (JPanel) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PANEL_LOGIN);
		assertTrue("Deberia haber vuelto al panel de login", panelLogin != null);
	}
	

	@Test
	public void testConfirmaContrasenaMal()
	{
		robot.delay(TestUtil.getDelay());
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_USSER_NAME);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_REAL_NAME);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_PASSWORD);
		JTextField repPass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("Gian", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		TestUtil.clickComponent(repPass, robot);
		TestUtil.tipeaTexto("password", robot);
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Gian Magliotti", robot);
		TestUtil.clickComponent(aceptarReg, robot);	
		
		assertEquals("Mensaje incorrecto, deberia mostrar"+Mensajes.PASS_NO_COINCIDE.getValor(), Mensajes.PASS_NO_COINCIDE.getValor(), optionPane.getMensaje());	
	}

	@Test
	public void testClienteCancelaRegistro()
	{
		robot.delay(TestUtil.getDelay());
		JButton cancelarRegistro = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_CANCELAR);
		TestUtil.clickComponent(cancelarRegistro, robot);
		robot.delay(TestUtil.getDelay());
		JPanel panelLogin = (JPanel) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PANEL_LOGIN);
		assertTrue("Deberia haber vuelto al panel de login", panelLogin != null);
	}
	
	@Test
	public void testPanelRegistroJTextFieldsvacios()
	{
		robot.delay(TestUtil.getDelay());
		robot.delay(TestUtil.getDelay());
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_USSER_NAME);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_REAL_NAME);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_PASSWORD);
		JTextField repPass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		JButton cancelarRegistro = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_CANCELAR);

		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("usuario", robot);
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("nombre", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("pass", robot);
		TestUtil.clickComponent(repPass, robot);
		TestUtil.tipeaTexto("repPass", robot);
		TestUtil.clickComponent(cancelarRegistro, robot);
	
		robot.delay(TestUtil.getDelay());
		JButton registrar = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REGISTRAR);
		TestUtil.clickComponent(registrar,robot);
		
		 List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
				 new SimpleEntry<>("El JTextField usuario deberia estar vacio", usuario.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField nombre deberia estar vacio", nombre.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField pass deberia estar vacio", pass.getText().isEmpty()),
				 new SimpleEntry<>("El JTextField repPass deberia estar vacio", repPass.getText().isEmpty())
			);
		 TestUtil.reportarErrores(aserciones);
		
	}
}
