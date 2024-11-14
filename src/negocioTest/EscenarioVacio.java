package negocioTest;

import static util.Constantes.ZONA_PELIGROSA;

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

public class EscenarioVacio {
	public Empresa empresa;
	public ArrayList<Pedido> pedidosTest;
	public ArrayList<Cliente> clientesTest;
	public ArrayList<Vehiculo> vehiculosTest;
	public ArrayList<Chofer> choferesTest;
	public ArrayList<Viaje> viajesIniciadosTest;
	public ArrayList<Viaje> viajesTerminadosTest;
	
	
	public void agregarViajesIniciados(ArrayList<Viaje> viajesIniciadosDatos) {
		for (Viaje viaje : viajesIniciadosDatos) {
		    try {
		    	empresa.agregarPedido(viaje.getPedido());
		    } catch(ClienteConPedidoPendienteException | ClienteConViajePendienteException 
		    		| ClienteNoExisteException | SinVehiculoParaPedidoException e) {
		    }
		    
		   	try {
				empresa.crearViaje(viaje.getPedido(), viaje.getChofer(), viaje.getVehiculo());
				viajesIniciadosTest.add(empresa.getViajesIniciados().get(viaje.getPedido().getCliente()));
			} catch (PedidoInexistenteException | ChoferNoDisponibleException | VehiculoNoDisponibleException
					| VehiculoNoValidoException | ClienteConViajePendienteException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// hay que cambiar este metodo
	
	public void agregarViajesTerminados(ArrayList<Viaje> viajesTerminadosDatos) {
		for (Viaje viaje : viajesTerminadosDatos) {
			Cliente clienteViaje = viaje.getPedido().getCliente();
		    try {
		    	empresa.agregarPedido(viaje.getPedido());
		    } catch(ClienteConPedidoPendienteException | ClienteConViajePendienteException 
		    		| ClienteNoExisteException | SinVehiculoParaPedidoException e) {
		    	System.out.println(e);
		    }
		    
		   	try {
				empresa.crearViaje(viaje.getPedido(), viaje.getChofer(), viaje.getVehiculo());
				viajesTerminadosTest.add(empresa.getViajesIniciados().get(viaje.getPedido().getCliente()));
			} catch (PedidoInexistenteException | ChoferNoDisponibleException | VehiculoNoDisponibleException
					| VehiculoNoValidoException | ClienteConViajePendienteException e) {

			}
		   	
		   	try {
				empresa.login(clienteViaje.getNombreUsuario(), clienteViaje.getPass());
			} catch (UsuarioNoExisteException | PasswordErroneaException e) {
				System.out.println(e);
			}
		   	
		   	try {
				empresa.pagarYFinalizarViaje(4); 
			} catch (ClienteSinViajePendienteException e) {
			} 
		   	
		   	empresa.logout();
			
		}
	}
	
	public void agregarPedidos() {
		for (Pedido pedido : pedidosTest) {
		    try {
		    	empresa.agregarPedido(pedido);
		    } catch(ClienteConPedidoPendienteException | ClienteConViajePendienteException 
		    		| ClienteNoExisteException | SinVehiculoParaPedidoException e) {
		    }
		}
	}
	
	public void agregarChoferes() {
		for (Chofer chofer : choferesTest) {
		    try {
		    	empresa.agregarChofer(chofer);
		    } catch (ChoferRepetidoException e) {
		    }
		}
	}
	
	// No se si aca capaz deberia hacer una clase cliente propia para no depender de los metodos que testeamos
	public void agregarClientes(ArrayList<Cliente> clientesDatos) {
		for (Cliente cliente : clientesDatos) {
			try {
				empresa.agregarCliente(cliente.getNombreUsuario(), cliente.getPass(), cliente.getNombreReal());
				clientesTest.add(empresa.getClientes().get(cliente.getNombreUsuario()));
			} catch (UsuarioYaExisteException e) {
			}
		}
	}
	
	public void agregarVehiculos() {
		for (Vehiculo vehiculo : vehiculosTest) {
			try {
				empresa.agregarVehiculo(vehiculo);
			} catch (VehiculoRepetidoException e) {
			}
		}
	}
	
	public void setUp() {
		empresa = Empresa.getInstance();
		clientesTest = new ArrayList<Cliente>();
		pedidosTest = new ArrayList<Pedido>();
		vehiculosTest = new ArrayList<Vehiculo>();
		choferesTest = new ArrayList<Chofer>();
		viajesTerminadosTest = new ArrayList<Viaje>();
		viajesIniciadosTest = new ArrayList<Viaje>();
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
