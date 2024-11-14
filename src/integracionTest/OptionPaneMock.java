package integracionTest;

import vista.IOptionPane;

public class OptionPaneMock implements IOptionPane{
	private String message = null;
	
	public OptionPaneMock() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ShowMessage(String message) {
		this.message = message;	
	}
	
	public String getMensaje()
	{
		return this.message;
	}

}
