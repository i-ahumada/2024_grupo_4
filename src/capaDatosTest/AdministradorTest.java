package capaDatosTest;

import modeloDatos.*;
import org.junit.Test;
import util.Constantes;
import static org.junit.Assert.assertEquals;

public class AdministradorTest {
	public final String ADMIN = "admin";
	
	@Test
	public void getInstance_Adminitrador() {
		Administrador admin1 = Administrador.getInstance();
		Administrador admin2 = Administrador.getInstance();
		
		assertEquals("Las referencias deberian ser iguales",admin1,admin2);
	}
	
	@Test
	public void getNombreUsuario() {
		assertEquals("El nombre del administrador deberia ser admin",Administrador.getInstance().getNombreUsuario(),"admin");
	}
	
	@Test
	public void getPass() {
		assertEquals("El password del administrador deberia ser admin",Administrador.getInstance().getPass(),"admin");
	}
}
