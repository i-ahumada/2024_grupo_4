package negocioTest;

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

public class Escenario3 extends EscenarioVacio{
	
	
	public void setUp() {
		super.setUp();		
		
		ArrayList<Cliente> clientesDatos = new ArrayList<Cliente>();
		
		clientesDatos.add(new Cliente("Tesla", "1234", "Nikola Tesla"));
		clientesDatos.add(new Cliente("Mandarina", "miau", "Guchiuan"));
		
		agregarClientes(clientesDatos);
		
		vehiculosTest.add(new Combi("AC789BB" , 10, false));
		vehiculosTest.add(new Auto("ZZ777AA" , 4, true));
		vehiculosTest.add(new Moto( "HH111HH"));
		
		agregarVehiculos();
		
		choferesTest.add(new ChoferPermanente("987789", "Fangio", 1900, 0));
		
		agregarChoferes();
		
		pedidosTest.add(new  Pedido (clientesTest.get(0), 4, true, false, 50, ZONA_PELIGROSA));
		
		agregarPedidos();
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
