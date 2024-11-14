package integracionTest;

import java.util.ArrayList;
import java.util.HashMap;

import controlador.Controlador;
import modeloNegocio.Empresa;
import persistencia.*;
import util.Constantes;
import modeloDatos.*;


public class Escenario {
	public VistaMock vista;
	public Controlador controlador;
	public Empresa empresa;
	public OptionPaneMock optionPane;
	public IPersistencia persistencia;
	
	
	public Escenario() {
	}

	
	public void setUp() throws Exception
	{
		empresa = Empresa.getInstance();
		optionPane = new OptionPaneMock();
		controlador = new Controlador();
		vista = new VistaMock(controlador, optionPane);	
		vista.setUsserName("Eloncito");
		vista.setRegUsserName("pepito45");
		vista.setDNIChofer("11888555");
		vista.setPassword("password");
	    vista.setRegConfirmPassword(vista.getPassword());
	    vista.setPatente("ZZ444ZZ");
		persistencia = new PersistenciaBIN();
		controlador.setVista(vista);
		controlador.setPersistencia(persistencia);
		
		Cliente cliente1 = new Cliente("Mandarina","miau","Mandarina");
		Cliente cliente2 = new Cliente(vista.getUsserName(), vista.getPassword(), vista.getUsserName());
		Cliente cliente3 = new Cliente("cr7","misterChampions","Ronaldo");
		empresa.agregarCliente(cliente1.getNombreUsuario(),cliente1.getPass(), cliente1.getNombreReal());
		empresa.agregarCliente(cliente2.getNombreUsuario(),cliente2.getPass(), cliente2.getNombreReal());
		empresa.agregarCliente(cliente3.getNombreUsuario(), cliente3.getPass(), cliente3.getNombreReal());

		Chofer chofer1 = new ChoferTemporario("44555666","Toretto");
		Chofer chofer2 =  new ChoferTemporario("33888777","Colapinto");
		Vehiculo vehiculo1 = new Combi("AA777AA", 9, true);
		Vehiculo vehiculo2 = new Auto("KK999KK", 4, true);
		Pedido pedido = new Pedido(empresa.getClientes().get("Mandarina"), 9, true, true, 10, Constantes.ZONA_STANDARD);
		Pedido pedido2 = new Pedido(empresa.getClientes().get("cr7"),4,false,false,5,Constantes.ZONA_SIN_ASFALTAR);

			
		empresa.agregarChofer(chofer1);
		empresa.agregarChofer(chofer2);
		empresa.agregarVehiculo(vehiculo1);
		empresa.agregarVehiculo(vehiculo2);
		empresa.agregarPedido(pedido);
		empresa.agregarPedido(pedido2);
        empresa.login("Mandarina", "miau");
		empresa.crearViaje(pedido, chofer1, vehiculo1);
		
		vista.setPedidoSeleccionado(pedido2);
		vista.setChoferDisponibleSeleccionado(chofer2);
	    vista.setVehiculoDisponibleSeleccionado(vehiculo2);
	
	}
	
	public void tearDown()
	{
		controlador = null;
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
		vista = null;
	}
	
	
}

	

