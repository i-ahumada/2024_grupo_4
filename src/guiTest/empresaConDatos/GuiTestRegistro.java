package guiTest.empresaConDatos;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import guiTest.TestUtil;
import integracionTest.OptionPaneMock;
import modeloDatos.Cliente;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class GuiTestRegistro {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	OptionPaneMock optionPane;
	
	public GuiTestRegistro() {
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
		controlador.getVista().setOptionPane(this.optionPane);
		empresa.agregarCliente("Mandarina","12345678","Mr Mandarina");
		robot.delay(TestUtil.getDelay());
		JButton registrar = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REGISTRAR);
		TestUtil.clickComponent(registrar,robot);
	}

	@After
	public void tearDown()
	{
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
		empresa.setClientes(new HashMap<String,Cliente>());
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
		
		JPanel panelLogin = (JPanel) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PANEL_LOGIN);
		assertTrue("Deberia haber vuelto al panel de login", panelLogin != null);
	}
	
	@Test
	public void testRegistroUsuarioRepetido()
	{
		robot.delay(TestUtil.getDelay());
		JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_USSER_NAME);
		JTextField nombre = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_REAL_NAME);
		JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_PASSWORD);
		JTextField repPass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		JButton aceptarReg = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		
		TestUtil.clickComponent(usuario, robot);
		TestUtil.tipeaTexto("Mandarina", robot);
		TestUtil.clickComponent(pass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		TestUtil.clickComponent(repPass, robot);
		TestUtil.tipeaTexto("12345678", robot);
		TestUtil.clickComponent(nombre, robot);
		TestUtil.tipeaTexto("Mr Mandarina", robot);
		TestUtil.clickComponent(aceptarReg, robot);	
		
		assertEquals("Mensaje incorrecto, deberia mostrar"+Mensajes.USUARIO_REPETIDO.getValor(),Mensajes.USUARIO_REPETIDO.getValor(),optionPane.getMensaje() );
	}
	
	@Test
	public void testRegistroRepiteContrasenaMal()
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
	
}
