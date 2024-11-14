package integracionTest;
import java.awt.event.ActionListener;

import modeloDatos.Chofer;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import util.Constantes;
import vista.IOptionPane;
import vista.IVista;
import vista.Ventana;

public class VistaMock implements IVista {
	private ActionListener actionListener;
	private Pedido pedidoSeleccionado;
	private Vehiculo vehiculoDisponibleSeleccionado;
	private IOptionPane optionPane;
	private Chofer choferDisponible;
	private String contrasena;
	private String confirmContrasena;
	private String patente;
	private String dniChofer;
	private String username;
	private String regusername;
	
	public VistaMock(ActionListener actionListener, IOptionPane optionPane) 
	{
		this.actionListener = actionListener;
		this.optionPane = optionPane;
	}
	
	@Override
	public void actualizar() {
	
		
	}

	@Override
	public void addActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	@Override
	public int getAnioChofer() {
		return 2024;
	}

	@Override
	public int getCalificacion() {
		return 4;
	}

	@Override
	public int getCantKm() {
		return 100;
	}

	@Override
	public int getCantidadPax() {
		return 4;
	}

	@Override
	public Chofer getChoferDisponibleSeleccionado() {
		return this.choferDisponible;
	}
	

	public void setChoferDisponibleSeleccionado(Chofer chofer) {
		this.choferDisponible = chofer;
	}

	@Override
	public String getDNIChofer() {
		return this.dniChofer;
	}
	

	public void setDNIChofer(String dniChofer) {
		this.dniChofer = dniChofer;
	}

	@Override
	public int getHijosChofer() {
		return 2;
	}

	@Override
	public String getNombreChofer() {
		return "Martinez";
	}

	@Override
	public IOptionPane getOptionPane() {
		return this.optionPane;
	}

	@Override
	public String getPassword() {
		return this.contrasena;
	}
	

	public void setPassword(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String getPatente() {
		return this.patente;
	}
	
	public void setPatente(String patente) {
		this.patente = patente;
	}

	@Override
	public Pedido getPedidoSeleccionado() {
		return this.pedidoSeleccionado;
	}
	
	
	public void setPedidoSeleccionado(Pedido pedido) {
		this.pedidoSeleccionado = pedido;
	}

	@Override
	public int getPlazas() {
		return 4;
	}

	@Override
	public String getRegConfirmPassword() {
		return this.confirmContrasena;
	}
	
	
	public void setRegConfirmPassword(String confirmContrasena) {
		this.confirmContrasena = confirmContrasena;
	}

	@Override
	public String getRegNombreReal() {
		return "Fangio";
	}

	@Override
	public String getRegPassword() {
		return "password";
	}

	@Override
	public String getRegUsserName() {
		return this.regusername;
	}	
	

	public void setRegUsserName(String username) {
		this.regusername = username;
	}	

	@Override
	public String getTipoChofer() {
		return Constantes.PERMANENTE;
	}

	@Override
	public String getTipoVehiculo() {
		return Constantes.AUTO;
	}

	@Override
	public String getTipoZona() {
		return Constantes.ZONA_PELIGROSA;
	}

	@Override
	public String getUsserName() {
		return this.username;
	}
	

	public void setUsserName(String username) {
		this.username = username;
	}


	@Override
	public Vehiculo getVehiculoDisponibleSeleccionado() {
		return this.vehiculoDisponibleSeleccionado;
	}
	
	
	public void setVehiculoDisponibleSeleccionado(Vehiculo vehiculo) {
		 this.vehiculoDisponibleSeleccionado = vehiculo;
	}

	@Override
	public boolean isPedidoConBaul() {
		return true;
	}

	@Override
	public boolean isPedidoConMascota() {
		return true;
	}

	@Override
	public boolean isVehiculoAptoMascota() {
		return true;
	}

	@Override
	public void logearUsuario() {
		
	}

	@Override
	public void setOptionPane(IOptionPane optionPane) {
		this.optionPane = optionPane;
	}

	public void setChoferDesocupado(Chofer chofer2) {
		// TODO Auto-generated method stub
		
	}

}
