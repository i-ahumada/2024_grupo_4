package guiTest;

import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JList;
import javax.swing.JTextField;

public class TestUtil {
	private static int delay = 20;
	
	public static int getDelay() {
		return delay;
	}

	public static void setDelay(int delay) {
		TestUtil.delay = delay;
	}

	public static void borrarJTextField(JTextField text, Robot robot) {
		Point punto = null;
		if(text != null) {
			robot.delay(getDelay());
			punto = text.getLocationOnScreen();
			robot.mouseMove(punto.x, punto.y);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.delay(getDelay());
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(getDelay());
			while(!text.getText().isEmpty()) {
				robot.delay(getDelay());
				robot.keyPress(KeyEvent.VK_DELETE);
				robot.delay(getDelay());
				robot.keyRelease(KeyEvent.VK_DELETE);
			}
		}
	}
	
	public static void clickComponent(Component component, Robot robot) {
		Point  punto = TestUtil.getCentro(component);
		robot.mouseMove(punto.x, punto.y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(getDelay());
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(getDelay());
	}
	
	public static Point getCentro(Component component) {
		Point retorno = null;
		if(component != null)
			retorno = component.getLocationOnScreen();
		retorno.x += component.getWidth()/2;
		retorno.y += component.getHeight()/2;
		return retorno;
	}
	
	public static void tipeaTexto(String text, Robot robot) {
		String mayusculas = text.toUpperCase();
		char letras[] = mayusculas.toCharArray();
		for(int i = 0; i < letras.length; i++) {
			robot.delay(getDelay());
			if(text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')
				robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(letras[i]);
			robot.delay(getDelay());
			robot.keyRelease(letras[i]);
			if(text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')
				robot.keyRelease(KeyEvent.VK_SHIFT);
		}
		
	}
	
	public static Component getComponentForName(Component parent, String name) {
		Component retorno = null;
		if(parent.getName() != null && parent.getName().equals(name))
			retorno = parent;
		else {
			if(parent instanceof Container) {
				Component[] hijos = ((Container) parent).getComponents();
				int i=0;
				while(i<hijos.length && retorno == null) {
					retorno = getComponentForName(hijos[i],name);
					i++;
				}
					
			}
		}
		return retorno;
	}
	
	public static void seleccionarElementoJList(JList<?> lista, int indice, Robot robot) {
	    if (lista != null && indice >= 0 && indice < lista.getModel().getSize()) {
	        // Obtener la ubicación de la JList
	        Point puntoLista = lista.getLocationOnScreen();
	        
	        // Calcular la altura de cada elemento (asumiendo que todos tienen la misma altura)
	        int alturaElemento = lista.getCellBounds(indice, indice).height;
	        
	        // Calcular la posición Y del elemento que queremos seleccionar
	        int posY = puntoLista.y + lista.getCellBounds(indice, indice).y;

	        // Mover el ratón a la posición del elemento y hacer clic
	        robot.mouseMove(puntoLista.x + 5, posY + (alturaElemento / 2)); // Ajuste de 5px para centrar
	        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	        robot.delay(getDelay());
	        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        robot.delay(getDelay());
	    }
	}
	
	/**
	 * Metodo utilizado para reportar errores en tests que tienen mas de un assert. 
	 * De esta forma pueden verse todos los fallos y no solo el del primer assert que falle
	 * @param aserciones
	 */
	public static void reportarErrores(List<SimpleEntry<String, Boolean>> aserciones) {
		StringBuilder errores = new StringBuilder();
		for (SimpleEntry<String, Boolean> asercion : aserciones) {
			if (!asercion.getValue()) {
				errores.append(asercion.getKey()).append("\n");
			}
		}
		
		if (errores.length() > 0) {
			fail("Errores encontrados:\n" + errores.toString());
		}
	}

}
