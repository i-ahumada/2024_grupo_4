package guiTest.empresaConDatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static util.Constantes.ZONA_PELIGROSA;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import guiTest.TestUtil;
import integracionTest.OptionPaneMock;
import modeloDatos.*;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class GuiTestCliente {
	Robot robot;
	Controlador controlador;
	Empresa empresa;
	OptionPaneMock optionPane;
	
	public GuiTestCliente() {
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
		empresa.agregarCliente("Gian","password","Gian Magliotti");
		Chofer chofer = new ChoferTemporario("Fangio","1000");
		empresa.agregarChofer(chofer);
		Vehiculo vehiculo = new Combi("JJ777JJ",9,false);
		Vehiculo vehiculo2 = new Combi("FF777FF",9,false);
		empresa.agregarVehiculo(vehiculo);
		empresa.agregarVehiculo(vehiculo2);
		Pedido pedido  = new Pedido(empresa.getClientes().get("Gian"), 4, false, true, 50, ZONA_PELIGROSA);
		empresa.agregarPedido(pedido);
		empresa.crearViaje(pedido, chofer, vehiculo);

		robot.delay(TestUtil.getDelay());
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
		empresa.setChoferes(new HashMap<String,Chofer>());
		empresa.setChoferesDesocupados(new ArrayList<Chofer>());
		empresa.setVehiculos(new HashMap<String,Vehiculo>());
		empresa.setVehiculosDesocupados(new ArrayList<Vehiculo>());
		empresa.setPedidos(new HashMap<Cliente,Pedido>());
		empresa.setViajesTerminados(new ArrayList<Viaje>());
	}
	
	@Test
	public void testNuevoPedidoExitoso() {
	    robot.delay(TestUtil.getDelay());
	    JButton nuevoPedido = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
	    JTextField pasajeros = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_PAX);
	    JTextField km = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_KM);
	    JTextField calificacion = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
	    JRadioButton zonaSinAsfalto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
	    JRadioButton zonaPeligrosa = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.ZONA_PELIGROSA);
	    JRadioButton zonaEstandar = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.ZONA_STANDARD);
	    JTextArea pedidoActual = (JTextArea) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PEDIDO_O_VIAJE_ACTUAL);
	    JCheckBox baul = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_BAUL);
	    JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_MASCOTA);
	    JTextField valor = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.VALOR_VIAJE);
	    
	    TestUtil.clickComponent(baul, robot);
	    TestUtil.clickComponent(zonaPeligrosa, robot);
	    TestUtil.clickComponent(pasajeros, robot);
	    TestUtil.tipeaTexto("4", robot);
	    TestUtil.clickComponent(km, robot);
	    TestUtil.tipeaTexto("4", robot);
	    TestUtil.clickComponent(nuevoPedido, robot);
	    
	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("El JTextField cant pax debería estar vacío", pasajeros.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField km debería estar vacío", km.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField valor debería estar vacío", valor.getText().isEmpty()),
	        new SimpleEntry<>("El JTextArea pedido actual debería contener el pedido nuevo", !pedidoActual.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField cant pax debería estar deshabilitado", !pasajeros.isEnabled()),
	        new SimpleEntry<>("El JTextField km debería estar deshabilitado", !km.isEnabled()),
	        new SimpleEntry<>("El JRadioButton zonaSinAsfalto debería estar deshabilitado", !zonaSinAsfalto.isEnabled()),
	        new SimpleEntry<>("El JRadioButton zonaPeligrosa debería estar deshabilitado", !zonaPeligrosa.isEnabled()),
	        new SimpleEntry<>("El JRadioButton zonaEstandar debería estar deshabilitado", !zonaEstandar.isEnabled()),
	        new SimpleEntry<>("El JCheckBox baul debería estar deshabilitado", !baul.isEnabled()),
	        new SimpleEntry<>("El JCheckBox mascota debería estar deshabilitado", !mascota.isEnabled()),
	        new SimpleEntry<>("El JTextField calificacion debería estar deshabilitado", !calificacion.isEnabled())
	    );

	    TestUtil.reportarErrores(aserciones);
	}

	@Test
	public void testNuevoPedidoNoSatisfacible() {
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
	    
	    assertTrue("Mensaje incorrecto, deberia mostrar " + Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(),
	    		Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor().equals(optionPane.getMensaje()));
	}

	@Test
	public void calificarPagar() {
	    robot.delay(TestUtil.getDelay());
	    JButton nuevoPedido = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NUEVO_PEDIDO);
	    JTextField pasajeros = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_PAX);
	    JTextField km = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CANT_KM);
	    JTextField calificacion = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
	    JRadioButton zonaSinAsfalto = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
	    JRadioButton zonaPeligrosa = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.ZONA_PELIGROSA);
	    JRadioButton zonaEstandar = (JRadioButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.ZONA_STANDARD);
	    JTextArea pedidoActual = (JTextArea) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PEDIDO_O_VIAJE_ACTUAL);
	    JCheckBox baul = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_BAUL);
	    JCheckBox mascota = (JCheckBox) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CHECK_MASCOTA);
	    JTextField valor = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.VALOR_VIAJE);
	    JButton calificarPagar = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CALIFICAR_PAGAR);
	    JList<Viaje> historicoViajes = (JList<Viaje>) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LISTA_VIAJES_CLIENTE);
	    JButton cerrarSesion = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);
	    
	    TestUtil.clickComponent(cerrarSesion, robot);
	    JTextField usuario = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.NOMBRE_USUARIO);
	    JTextField pass = (JTextField) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.PASSWORD);
	    JButton aceptarLogin = (JButton) TestUtil.getComponentForName((Ventana)controlador.getVista(), Constantes.LOGIN);
	    
	    TestUtil.clickComponent(usuario, robot);
	    TestUtil.tipeaTexto("Gian", robot);
	    TestUtil.clickComponent(pass, robot);
	    TestUtil.tipeaTexto("password", robot);
	    TestUtil.clickComponent(aceptarLogin, robot);
	    
	    TestUtil.clickComponent(calificacion, robot);
	    TestUtil.tipeaTexto("5", robot);
	    TestUtil.clickComponent(calificarPagar, robot);
	    
	    Viaje viajeFinalizado = empresa.getViajesTerminados().get(0);
	    boolean encontrado = false;

	    for (int i = 0; i < historicoViajes.getModel().getSize(); i++) {
	        if (historicoViajes.getModel().getElementAt(i).equals(viajeFinalizado)) {
	            encontrado = true;
	            break;
	        }
	    }

	    List<SimpleEntry<String, Boolean>> aserciones = Arrays.asList(
	        new SimpleEntry<>("El JTextField calificacion debería estar vacío", calificacion.getText().isEmpty()),
	        new SimpleEntry<>("El JTextField valor debería estar vacío", valor.getText().isEmpty()),
	        new SimpleEntry<>("Se deberia haber eliminado el pedido del JTextArea pedido actual", pedidoActual.getText().isEmpty()),
	        new SimpleEntry<>("El JList historico de viajes debería contener el viaje recien finalizado", encontrado),
	        new SimpleEntry<>("El JTextField cant pax debería estar habilitado", pasajeros.isEnabled()),
	        new SimpleEntry<>("El JTextField km debería estar habilitado", km.isEnabled()),
	        new SimpleEntry<>("El JTextField calificacion debería estar deshabilitado", !calificacion.isEnabled()),
	        new SimpleEntry<>("El JRadioButton zonaSinAsfalto debería estar habilitado", zonaSinAsfalto.isEnabled()),
	        new SimpleEntry<>("El JRadioButton zonaPeligrosa debería estar habilitado", zonaPeligrosa.isEnabled()),
	        new SimpleEntry<>("El JRadioButton zonaEstandar debería estar habilitado", zonaEstandar.isEnabled()),
	        new SimpleEntry<>("El JCheckBox baul debería estar habilitado", baul.isEnabled()),
	        new SimpleEntry<>("El JCheckBox mascota debería estar habilitado", mascota.isEnabled())
	    );

	    TestUtil.reportarErrores(aserciones);
	}
	


}
