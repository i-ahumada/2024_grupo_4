package persistenciaTest;

import static util.Constantes.ZONA_PELIGROSA;
import static util.Constantes.ZONA_SIN_ASFALTAR;
import static util.Constantes.ZONA_STANDARD;

import java.util.ArrayList;
import java.util.HashMap;

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
import negocioTest.Escenario4;
import persistencia.EmpresaDTO;

public class EscenarioDTO extends EscenarioDTOVacio {
	
	public void setUp() {
		super.setUp();
		ArrayList<Cliente> clientesDatos = new ArrayList<Cliente>();
		clientesDatos.add(new Cliente("Tesla", "1234", "Nikola Tesla"));
		clientesDatos.add(new Cliente("Mandarina", "miau", "Guchiuan"));
		clientesDatos.add(new Cliente("Eloncito", "$$$", "Elon Musk"));
		clientesDatos.add(new Cliente("Fito", "teknikolor","Fito Paez"));
		
		agregarClientes(clientesDatos);
		
		choferesTest.add(new ChoferPermanente("987789", "Fangio", 1900, 0));
		choferesTest.add(new ChoferTemporario ( "23900189" , "Colapinto"));
		choferesTest.add(new ChoferPermanente("776677", "Toretto", 2000, 4));
		choferesTest.add(new ChoferTemporario ( "93456712" , "Mercedes"));
		
		agregarChoferes();
		
		vehiculosTest.add(new Combi("AC789BB" , 10, false));
		vehiculosTest.add(new Auto("ZZ777AA" , 4, true));
		vehiculosTest.add(new Moto( "HH111HH"));
		vehiculosTest.add(new Combi("KL898LK" , 8, false));
		
		agregarVehiculos();
		
		ArrayList<Viaje> viajesDatos = new ArrayList<Viaje>();
		
		viajesDatos.add(new Viaje(new Pedido(clientesTest.get(1), 1, false, false, 290, ZONA_SIN_ASFALTAR), choferesTest.get(0) ,vehiculosTest.get(2)));
		viajesDatos.add(new Viaje(new Pedido(clientesTest.get(0), 3, false, false, 90, ZONA_STANDARD), choferesTest.get(1) ,vehiculosTest.get(0)));
		viajesDatos.add(new Viaje(new Pedido(clientesTest.get(1), 8, false, false, 90, ZONA_SIN_ASFALTAR), choferesTest.get(1) ,vehiculosTest.get(3)));
		
		agregarViajesTerminados(viajesDatos);
		
		viajesDatos = new ArrayList<Viaje>();
		
		viajesDatos.add(new Viaje(new Pedido(clientesTest.get(2), 1, false, true, 1000, ZONA_PELIGROSA), choferesTest.get(2) ,vehiculosTest.get(1)));
		viajesDatos.add(new Viaje(new Pedido(clientesTest.get(1), 1, false, false, 100, ZONA_PELIGROSA), choferesTest.get(3) ,vehiculosTest.get(2)));
		viajesDatos.add(new Viaje(new Pedido(clientesTest.get(3), 9, false, true, 800, ZONA_STANDARD), choferesTest.get(1) ,vehiculosTest.get(0)));
		
		agregarViajesIniciados(viajesDatos);
		
		pedidosTest.add(new Pedido(clientesTest.get(0), 4, true, false, 50, ZONA_PELIGROSA));
		
		agregarPedidos();
		
		empresaDTO.setChoferes(new HashMap<String, Chofer>(empresa.getChoferes()));
		empresaDTO.setChoferesDesocupados(new ArrayList<Chofer>(empresa.getChoferesDesocupados()));
		empresaDTO.setClientes(new HashMap<String, Cliente>(empresa.getClientes()));
		empresaDTO.setPedidos(new HashMap<Cliente, Pedido>(empresa.getPedidos()));
		empresaDTO.setUsuarioLogeado(empresa.getUsuarioLogeado());
		empresaDTO.setVehiculos(new HashMap<String, Vehiculo>(empresa.getVehiculos()));
		empresaDTO.setVehiculosDesocupados(new ArrayList<Vehiculo>(empresa.getVehiculosDesocupados()));
		empresaDTO.setViajesIniciados(new HashMap<Cliente, Viaje>(empresa.getViajesIniciados()));
		empresaDTO.setViajesTerminados(new ArrayList<Viaje>(empresa.getViajesTerminados()));
	}
}
