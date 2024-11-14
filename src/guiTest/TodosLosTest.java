package guiTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


import guiTest.enabledDisabled.*;
import guiTest.empresaConDatos.*;
import guiTest.empresaVacia.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    GuiTestAdminEnabledDisabled.class,
    GuiTestLoginEnabledDisabled.class,
    GuiTestRegistroEnabledDisabled.class,
    GuiTestClienteEnabledDisabled.class,
    GuiTestCliente.class,
    GuiTestAdmin.class,
    GuiTestLogin.class,
    GuiTestRegistro.class,
    GuiTestClienteVacia.class,
    GuiTestAdminVacia.class,
    GuiTestLoginVacia.class,
    GuiTestRegistroVacia.class
})
public class TodosLosTest {
}
