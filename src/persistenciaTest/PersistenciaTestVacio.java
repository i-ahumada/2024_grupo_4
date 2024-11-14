package persistenciaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;

public class PersistenciaTestVacio {
	private EscenarioDTOVacio escenario = new EscenarioDTOVacio();
	
    @Before
    public void setUp()
    {
		this.escenario.setUp();
    }
	
	@After
	public void tearDown()
	{
		this.escenario.tearDown();
	}
	
	@Test 
	public void testPersistenciaEmpresa() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			persistencia.abrirOutput("empresaTest.bin");
			persistencia.escribir(escenario.empresaDTO);
			persistencia.cerrarOutput();
			persistencia.abrirInput("empresaTest.bin");
			EmpresaDTO empresa = (EmpresaDTO) persistencia.leer();
			persistencia.cerrarInput();
			assertEquals("Las colecciones de choferes deberian ser iguales", escenario.empresaDTO.getChoferes(), empresa.getChoferes());
			assertEquals("Las colecciones de clientes deberian ser iguales", escenario.empresaDTO.getClientes(), empresa.getClientes());
			assertEquals("Las colecciones de choferes desocupados deberian ser iguales", escenario.empresaDTO.getChoferesDesocupados(), empresa.getChoferesDesocupados());
			assertEquals("Las colecciones de pedidos deberian ser iguales", escenario.empresaDTO.getPedidos(), empresa.getPedidos());
			assertEquals("Los usuarios loggeados deberian ser iguales", escenario.empresaDTO.getUsuarioLogeado(), empresa.getUsuarioLogeado());
			assertEquals("Las colecciones de vehiculos deberian ser iguales", escenario.empresaDTO.getVehiculos().entrySet(), empresa.getVehiculos().entrySet());
			assertEquals("Las colecciones de vehiculos desocupados deberian ser iguales", escenario.empresaDTO.getVehiculosDesocupados(), empresa.getVehiculosDesocupados());
			assertEquals("Las colecciones de viajes iniciados deberian ser iguales", escenario.empresaDTO.getViajesIniciados(), empresa.getViajesIniciados());
			assertEquals("Las colecciones de viajes terminados deberian ser iguales", escenario.empresaDTO.getViajesTerminados(), empresa.getViajesTerminados());
		} catch(FileNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
}
