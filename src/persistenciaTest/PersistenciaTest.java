package persistenciaTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import guiTest.TestUtil;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Combi;
import modeloDatos.Moto;
import modeloDatos.Viaje;

import static org.junit.Assert.*;

import modeloNegocio.Empresa;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import persistencia.EmpresaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

public class PersistenciaTest {
	private EscenarioDTO escenario = new EscenarioDTO();
	
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
	public void testEmpresaDTOfromEmpresa() {
		EmpresaDTO empresaDTOTest = UtilPersistencia.EmpresaDtoFromEmpresa();
		assertEquals("Las colecciones de choferes deberian ser iguales", escenario.empresaDTO.getChoferes(), empresaDTOTest.getChoferes());
		assertEquals("Las colecciones de clientes deberian ser iguales", escenario.empresaDTO.getClientes(), empresaDTOTest.getClientes());
		assertEquals("Las colecciones de choferes desocupados deberian ser iguales", escenario.empresaDTO.getChoferesDesocupados(), empresaDTOTest.getChoferesDesocupados());
		assertEquals("Las colecciones de pedidos deberian ser iguales", escenario.empresaDTO.getPedidos(), empresaDTOTest.getPedidos());
		assertEquals("Los usuarios loggeados deberian ser iguales", escenario.empresaDTO.getUsuarioLogeado(), empresaDTOTest.getUsuarioLogeado());
		assertEquals("Las colecciones de vehiculos deberian ser iguales", escenario.empresaDTO.getVehiculos(), empresaDTOTest.getVehiculos());
		assertEquals("Las colecciones de vehiculos desocupados deberian ser iguales", escenario.empresaDTO.getVehiculosDesocupados(), empresaDTOTest.getVehiculosDesocupados());
		assertEquals("Las colecciones de viajes iniciados deberian ser iguales", escenario.empresaDTO.getViajesIniciados(), empresaDTOTest.getViajesIniciados());
		assertEquals("Las colecciones de viajes terminados deberian ser iguales", escenario.empresaDTO.getViajesTerminados(), empresaDTOTest.getViajesTerminados());
	}
	
	@Test
	public void testEmpresafromEmpresaDTO() {
		UtilPersistencia.empresaFromEmpresaDTO(escenario.empresaDTO);
		Empresa empresa = Empresa.getInstance();
		assertEquals("Las colecciones de choferes deberian ser iguales", escenario.empresaDTO.getChoferes(), empresa.getChoferes());
		assertEquals("Las colecciones de clientes deberian ser iguales", escenario.empresaDTO.getClientes(), empresa.getClientes());
		assertEquals("Las colecciones de choferes desocupados deberian ser iguales", escenario.empresaDTO.getChoferesDesocupados(), empresa.getChoferesDesocupados());
		assertEquals("Las colecciones de pedidos deberian ser iguales", escenario.empresaDTO.getPedidos(), empresa.getPedidos());
		assertEquals("Los usuarios loggeados deberian ser iguales", escenario.empresaDTO.getUsuarioLogeado(), empresa.getUsuarioLogeado());
		assertEquals("Las colecciones de vehiculos deberian ser iguales", escenario.empresaDTO.getVehiculos(), empresa.getVehiculos());
		assertEquals("Las colecciones de vehiculos desocupados deberian ser iguales", escenario.empresaDTO.getVehiculosDesocupados(), empresa.getVehiculosDesocupados());
		assertEquals("Las colecciones de viajes iniciados deberian ser iguales", escenario.empresaDTO.getViajesIniciados(), empresa.getViajesIniciados());
		assertEquals("Las colecciones de viajes terminados deberian ser iguales", escenario.empresaDTO.getViajesTerminados(), empresa.getViajesTerminados());
	}
	
	@Test
	public void testCrearArchivo_Escritura() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			persistencia.abrirOutput("empresaTest.bin");
			File archivo = new File("empresaTest.bin");
			persistencia.cerrarOutput();
            assertTrue("Debería existir el archivo empresaTest.bin", archivo.exists());
		} catch(FileNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
	
	@Test
	public void testAbrirArchivoSinArchivo_Lectura() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			persistencia.abrirInput("empresaTest.bin");
			File archivo = new File("empresaTest.bin");
			persistencia.cerrarInput();
            assertTrue("No debería existir el archivo empresaTest.bin", archivo.exists());
		} catch(FileNotFoundException e) {
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
	
	@Test
	public void testLeerArchivoSinArchivo_Lectura() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			EmpresaDTO empresa = (EmpresaDTO) persistencia.leer();
            fail("Debería lanzar una excepcion FileNotFound");
		} catch(FileNotFoundException e) {
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
	
	@Test
	public void testEcsribirArchivoSinArchivo_Escritura() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			persistencia.escribir(escenario.empresaDTO);
            fail("Debería lanzar una excepcion FileNotFound");
		} catch(FileNotFoundException e) {
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
	
	
	@Test 
	public void testPersistenciaChoferPermanente() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
			persistencia.abrirOutput("empresaTest.bin");
			persistencia.escribir(empresaDTO);
			persistencia.cerrarOutput();
			persistencia.abrirInput("empresaTest.bin");
			EmpresaDTO empresaDTOLeido = (EmpresaDTO) persistencia.leer();
			UtilPersistencia.empresaFromEmpresaDTO(empresaDTOLeido);
			persistencia.cerrarInput();
			Empresa empresa = Empresa.getInstance();
			ChoferPermanente choferPE = (ChoferPermanente) empresaDTO.getChoferes().get("987789");
			ChoferPermanente choferPO = (ChoferPermanente) empresa.getChoferes().get("987789");
			
		    List<SimpleEntry<String, Boolean>> asercionesChoferPermanente = Arrays.asList(
			        new SimpleEntry<>("El anio de ingreso es distinto", choferPE.getAnioIngreso() == choferPO.getAnioIngreso()),
			        new SimpleEntry<>("La antiguedad de los choferes es distinta", choferPE.getAntiguedad() == choferPO.getAntiguedad()),
			        new SimpleEntry<>("La cantidad de hijos de los choferes es distinta", choferPE.getCantidadHijos() == choferPO.getCantidadHijos()),
			        new SimpleEntry<>("Los dni de los choferes son distintos", choferPE.getDni().equals(choferPO.getDni())),
			        new SimpleEntry<>("Los nombres de los choferes son distintos", choferPE.getNombre().equals(choferPO.getNombre())),
			        new SimpleEntry<>("El sueldo bruto de los choferes es distinto", choferPE.getSueldoBruto() == choferPO.getSueldoBruto()),
			        new SimpleEntry<>("El sueldo neto de los choferes es distinto", choferPE.getSueldoNeto() == choferPO.getSueldoNeto())			        
			    );

			TestUtil.reportarErrores(asercionesChoferPermanente);

		} catch(FileNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
	
	@Test 
	public void testPersistenciaChoferTemporario() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
			persistencia.abrirOutput("empresaTest.bin");
			persistencia.escribir(empresaDTO);
			persistencia.cerrarOutput();
			persistencia.abrirInput("empresaTest.bin");
			EmpresaDTO empresaDTOLeido = (EmpresaDTO) persistencia.leer();
			UtilPersistencia.empresaFromEmpresaDTO(empresaDTOLeido);
			persistencia.cerrarInput();
			Empresa empresa = Empresa.getInstance();

			ChoferTemporario choferTE = (ChoferTemporario) empresaDTO.getChoferes().get("23900189");
			ChoferTemporario choferTO = (ChoferTemporario) empresa.getChoferes().get("23900189");
			
		    List<SimpleEntry<String, Boolean>> asercionesChoferTemporario = Arrays.asList(
			        new SimpleEntry<>("Los dni de los choferes son distintos", choferTE.getDni().equals(choferTO.getDni())),
			        new SimpleEntry<>("Los nombres de los choferes son distintos", choferTE.getNombre().equals(choferTO.getNombre())),
			        new SimpleEntry<>("El sueldo bruto de los choferes es distinto", choferTE.getSueldoBruto() == choferTO.getSueldoBruto()),
			        new SimpleEntry<>("El sueldo neto de los choferes es distinto", choferTE.getSueldoNeto() == choferTO.getSueldoNeto())			        
			    );

			TestUtil.reportarErrores(asercionesChoferTemporario);
			
		} catch(FileNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
	
	@Test 
	public void testPersistenciaAuto() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
			persistencia.abrirOutput("empresaTest.bin");
			persistencia.escribir(empresaDTO);
			persistencia.cerrarOutput();
			persistencia.abrirInput("empresaTest.bin");
			EmpresaDTO empresaDTOLeido = (EmpresaDTO) persistencia.leer();
			UtilPersistencia.empresaFromEmpresaDTO(empresaDTOLeido);
			persistencia.cerrarInput();
			Empresa empresa = Empresa.getInstance();
			
			Auto autoE = (Auto) empresaDTO.getVehiculos().get("ZZ777AA");
			Auto autoO = (Auto) empresa.getVehiculos().get("ZZ777AA");
			
		    List<SimpleEntry<String, Boolean>> asercionesAuto = Arrays.asList(
			        new SimpleEntry<>("La cantidad de plazas del auto es distinta", autoE.getCantidadPlazas() == autoO.getCantidadPlazas()),
			        new SimpleEntry<>("La patente del auto es distinta", autoE.getPatente().equals(autoO.getPatente())),
			        new SimpleEntry<>("El valor de mascota del auto es distinta", autoE.isMascota() == autoO.isMascota())
			    );

			TestUtil.reportarErrores(asercionesAuto);
			
		} catch(FileNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
	
	@Test 
	public void testPersistenciaCombi() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
			persistencia.abrirOutput("empresaTest.bin");
			persistencia.escribir(empresaDTO);
			persistencia.cerrarOutput();
			persistencia.abrirInput("empresaTest.bin");
			EmpresaDTO empresaDTOLeido = (EmpresaDTO) persistencia.leer();
			UtilPersistencia.empresaFromEmpresaDTO(empresaDTOLeido);
			persistencia.cerrarInput();
			Empresa empresa = Empresa.getInstance();
			
			Combi combiE = (Combi) empresaDTO.getVehiculos().get("KL898LK");
			Combi combiO = (Combi) empresa.getVehiculos().get("KL898LK");
			
		    List<SimpleEntry<String, Boolean>> asercionesCombi = Arrays.asList(
			        new SimpleEntry<>("La cantidad de plazas de la combi es distinta", combiE.getCantidadPlazas() == combiO.getCantidadPlazas()),
			        new SimpleEntry<>("La patente de la combi es distinta", combiE.getPatente().equals(combiO.getPatente())),
			        new SimpleEntry<>("El valor de mascota de la combi es distinta", combiE.isMascota() == combiO.isMascota())
			    );

			TestUtil.reportarErrores(asercionesCombi);
			
		} catch(FileNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
	
	@Test 
	public void testPersistenciaMoto() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
			persistencia.abrirOutput("empresaTest.bin");
			persistencia.escribir(empresaDTO);
			persistencia.cerrarOutput();
			persistencia.abrirInput("empresaTest.bin");
			EmpresaDTO empresaDTOLeido = (EmpresaDTO) persistencia.leer();
			UtilPersistencia.empresaFromEmpresaDTO(empresaDTOLeido);
			persistencia.cerrarInput();
			Empresa empresa = Empresa.getInstance();
			
			Moto motoE = (Moto) empresaDTO.getVehiculos().get("HH111HH");
			Moto motoO = (Moto) empresa.getVehiculos().get("HH111HH");
			
		    List<SimpleEntry<String, Boolean>> asercionesMoto = Arrays.asList(
			        new SimpleEntry<>("La cantidad de plazas de la moto es distinta", motoE.getCantidadPlazas() == motoO.getCantidadPlazas()),
			        new SimpleEntry<>("La patente de la moto es distinta", motoE.getPatente().equals(motoO.getPatente())),
			        new SimpleEntry<>("El valor de mascota de la moto es distinta", motoE.isMascota() == motoO.isMascota())
			    );

			TestUtil.reportarErrores(asercionesMoto);
			
		} catch(FileNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
	
	@Test 
	public void testPersistenciaViaje() {
		PersistenciaBIN persistencia = new PersistenciaBIN(); 
		try {
			EmpresaDTO empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
			persistencia.abrirOutput("empresaTest.bin");
			persistencia.escribir(empresaDTO);
			persistencia.cerrarOutput();
			persistencia.abrirInput("empresaTest.bin");
			EmpresaDTO empresaDTOLeido = (EmpresaDTO) persistencia.leer();
			UtilPersistencia.empresaFromEmpresaDTO(empresaDTOLeido);
			persistencia.cerrarInput();
			Empresa empresa = Empresa.getInstance();
			
			Viaje viajeE = (Viaje) empresaDTO.getViajesTerminados().get(0);
			Viaje viajeO = (Viaje) empresa.getViajesTerminados().get(0);
			
			List<SimpleEntry<String, Boolean>> asercionesViajes = Arrays.asList(
					new SimpleEntry<>("La calificacion de los viajes es distinta", viajeE.getCalificacion() == viajeO.getCalificacion()),
					new SimpleEntry<>("Los choferes de los viajes son distintos", viajeE.getChofer().getDni().equals(viajeO.getChofer().getDni())),
					new SimpleEntry<>("El valor de los viajes son distintos", viajeE.getValor() == viajeO.getValor()),
					new SimpleEntry<>("El vehiculo de los viajes son distintos", viajeE.getVehiculo().getPatente().equals(viajeO.getVehiculo().getPatente())),
					new SimpleEntry<>("El cliente de los viajes son distintos", viajeE.getPedido().getCliente().getNombreUsuario().equals(viajeO.getPedido().getCliente().getNombreUsuario()))
					);
			
			TestUtil.reportarErrores(asercionesViajes);
			
		} catch(FileNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (IOException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("No deberia lanzar excepcion: "+ e.getMessage());
		}
	}
}
