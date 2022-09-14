package POO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GetBanco {
	
	private Connection con;
	public GetBanco() {
		this.con = new Conexao().getConnection();
	}
	

	
	
	public List<Produto>listarProduto(){
		try {
		List<Produto> lista = new ArrayList<>();
		String sql = "SELECT * FROM produto";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while( rs.next()) {
			Produto obj = new Produto();
			
			obj.setId(rs.getInt("id"));
			obj.setDescricao(rs.getString("descricao"));
			obj.setData(rs.getDate("data_cadastro"));
			lista.add(obj);
			
		}
		
		return lista;
			
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,"Erro: "+ e);
			return null;
			
		}
		
	}
	
	//#################### CREATE ##########################
	
	public void cadastrarProduto(Produto obj) {
		try {
			String sql=" INSERT INTO produto ( descricao, data_cadastro ) value (?,?) ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getDescricao());
			stmt.setDate(2, obj.getData());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não Cadastrado"+e);
			
		}
	}
	
	
	//###################  UPDATE  #############
	public void atualizarProduto(Produto obj) {
		try {
			String sql = "UPDATE produto SET descricao =? WHERE id =?";
			PreparedStatement  stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getDescricao());
			stmt.setInt(2, obj.getId());
			stmt.execute();
			stmt.close();
			
			 JOptionPane.showMessageDialog(null, "Dados Alterados!");
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Dados não Alterados"+ e);
	}
	}
	
		//###################  DELETE  #############
		public void deletarProduto(Produto obj) {
			try {
				String sql = "DELETE FROM produto WHERE id = ?";
				PreparedStatement  stmt = con.prepareStatement(sql);
				stmt.setInt(1, obj.getId());
				stmt.execute();
				stmt.close();
				
				 JOptionPane.showMessageDialog(null, "Dados Deletados!");
			} catch (Exception e) {
				 JOptionPane.showMessageDialog(null,"Dados não Deletados"+ e);
		}
		
}

}

	
	
	