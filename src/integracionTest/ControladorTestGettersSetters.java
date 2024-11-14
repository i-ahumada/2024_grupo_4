package integracionTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import persistencia.*;
import vista.*;
import controlador.Controlador;

public class ControladorTestGettersSetters {
	private Escenario escenario = new Escenario();
	
    @Before
    public void setUp()
    {
    	try {
			this.escenario.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@After
	public void tearDown()
	{
		this.escenario.tearDown();
	}
	
	@Test
	public void testSetFileName()
	{
		String filename = "Empresa.bin";
		escenario.controlador.setFileName(filename);
		assertEquals("La persistencia no fue correctamente seteada",escenario.controlador.getFileName(), filename);	
	}
	
	@Test
	public void testSetPersistencia()
	{
		PersistenciaBIN persistencia =  new PersistenciaBIN();
		escenario.controlador.setPersistencia(persistencia);
		assertEquals("La persistencia no fue correctamente seteada",escenario.controlador.getPersistencia(), persistencia);	
	}
	
	@Test
	public void testSetVista()
	{
		IVista vista = new Ventana();
		escenario.controlador.setVista(vista);
		assertEquals("La vista no fue correctamente seteada",escenario.controlador.getVista(), vista);	
	}
	
	@Test
	public void testGetFileName()
	{
		assertEquals("fileName no se recupero de manera correcta",escenario.controlador.getFileName(), "empresa.bin");	
	}
	
	@Test
	public void testGetPersistencia()
	{
		assertEquals("La persistencia no se recupero de manera correcta",escenario.controlador.getPersistencia(), escenario.persistencia);		
	}
	
	@Test
	public void testGetVista()
	{
		assertEquals("La vista no se recupero de manera correcta",escenario.controlador.getVista(), escenario.vista);	
	}

}
