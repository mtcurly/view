package POO;

import javax.swing.JOptionPane;

public class TestaConexao {
	
	public static void main(String[] args) {
		
		try {
			
			new Conexao().getConnection();
			
			JOptionPane.showMessageDialog(null, "Banco Conectado");
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Banco não conectado "+e);
			
		}
		
	}

}
