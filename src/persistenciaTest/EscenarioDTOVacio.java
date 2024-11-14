package persistenciaTest;

import static util.Constantes.ZONA_PELIGROSA;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import excepciones.ChoferNoDisponibleException;
import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.ClienteSinViajePendienteException;
import excepciones.PasswordErroneaException;
import excepciones.PedidoInexistenteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;
import negocioTest.EscenarioVacio;
import persistencia.EmpresaDTO;

public class EscenarioDTOVacio extends EscenarioVacio {
	public EmpresaDTO empresaDTO;

	
	public void setUp() {
		super.setUp();
		empresaDTO = new EmpresaDTO();
	}
	
	public void tearDown() {
		super.tearDown();
		empresaDTO = null;
		File archivo = new File("empresaTest.bin");
		if (archivo.exists()) {
			archivo.delete();
		}
	}
}
