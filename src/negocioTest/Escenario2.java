package negocioTest;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import modeloNegocio.Empresa;

import static org.junit.Assert.fail;
import static util.Constantes.*;

import java.util.ArrayList;
import java.util.HashMap;

import excepciones.ChoferNoDisponibleException;
import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.PedidoInexistenteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import excepciones.VehiculoRepetidoException;

public class Escenario2 extends EscenarioVacio {	
	public void setUp() {
		super.setUp();
		
		choferesTest.add(new ChoferPermanente("987789", "Fangio", 1900, 0));
		choferesTest.add(new ChoferTemporario("23900189" , "Colapinto"));
		
		agregarChoferes();
		
		vehiculosTest.add(new Auto("ZZ777AA" , 4, true));
		vehiculosTest.add(new Moto( "HH111HH"));
		
		agregarVehiculos();
		
		ArrayList<Cliente> clientesDatos = new ArrayList<Cliente>();
		
		clientesDatos.add(new Cliente("Tesla", "1234", "Nikola Tesla"));
		clientesDatos.add(new Cliente("Mandarina", "miau", "Guchiuan"));
		
		agregarClientes(clientesDatos);
		
		ArrayList<Viaje> viajesDatos = new ArrayList<Viaje>();
		
		viajesDatos.add(new Viaje(new Pedido(clientesTest.get(0), 4, false, true, 1000, ZONA_PELIGROSA), choferesTest.get(0), vehiculosTest.get(0)));
		
		agregarViajesIniciados(viajesDatos);
	}
	
	public void tearDown() {
		empresa.setChoferes(new HashMap<String,Chofer>());
		empresa.setChoferesDesocupados(new ArrayList<Chofer>());
		empresa.setClientes(new HashMap<String, Cliente>());
		empresa.setPedidos(new HashMap<Cliente, Pedido>());
		empresa.setUsuarioLogeado(null);
		empresa.setVehiculos(new HashMap<String, Vehiculo>());
		empresa.setVehiculosDesocupados(new ArrayList<Vehiculo>());
		empresa.setViajesIniciados(new HashMap<Cliente, Viaje>());
		empresa.setViajesTerminados(new ArrayList<Viaje>());
		empresa = null;
	}
}
