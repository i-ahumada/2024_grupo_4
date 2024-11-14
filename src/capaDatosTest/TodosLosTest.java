package capaDatosTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import capaDatosTest.*;


@RunWith(Suite.class)
@Suite.SuiteClasses({ AutoTest.class, AdministradorTest.class,
                      ChoferPermanenteTest.class, ChoferTemporarioTest.class, ChoferTest.class,ClienteTest.class,CombiTest.class
                      ,MotoTest.class, PedidoTest.class, ViajeTest.class})
public class TodosLosTest {

}
