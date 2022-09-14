package POO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textid;
	private JTextField textproduto;
	private JTextField textdata;
	private JTable tableProduto;
	
	private void desabilitTexfled() {
		
		textid.setEditable(false);
		textdata.setEditable(false);
		
	}
		private void habiliteText() {
			textproduto.setEditable(true);
			textid.setText(null);
			textdata.setText(null);
			textproduto.requestFocus();
		}
		
		private void limparCampo() {
					
				textproduto.setText("");
				textid.setText("");
				textdata.setText("");

		}
	
	
	public void listarProdutosNaTela() {
		
		GetBanco banco = new GetBanco();
		List<Produto> lista = banco.listarProduto();
		DefaultTableModel modelo = (DefaultTableModel) tableProduto.getModel();
		modelo.setNumRows(0);
		for( Produto prod: lista )  {
			modelo.addRow(new Object[] {
					prod.getId(),
					prod.getDescricao(),
					prod.getData(),
			});
		}
		
	}
	
	private void ExibitionTable() {
		
		
		DefaultTableModel dados = (DefaultTableModel) tableProduto.getModel();
		int row = tableProduto.getSelectedRow();
		
		if(dados.getValueAt(row, 0).toString() !="id") {
			textid.setText(dados.getValueAt(row, 0).toString());
			textproduto.setText(dados.getValueAt(row, 1).toString());
			textdata.setText(dados.getValueAt(row, 2).toString());
			
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProduto frame = new TelaProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaProduto() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				limparCampo();
				//Colocar o metodo de Listar Clientes aqui
				listarProdutosNaTela();
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ID = new JLabel("ID");
		ID.setBounds(10, 10, 30, 13);
		contentPane.add(ID);
		
		textid = new JTextField();
		textid.setBounds(33, 7, 30, 19);
		contentPane.add(textid);
		textid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Produto");
		lblNewLabel.setBounds(10, 49, 45, 13);
		contentPane.add(lblNewLabel);
		
		textproduto = new JTextField();
		textproduto.setBounds(79, 46, 219, 19);
		contentPane.add(textproduto);
		textproduto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Data de Cadastro");
		lblNewLabel_1.setBounds(79, 7, 101, 13);
		contentPane.add(lblNewLabel_1);
		
		textdata = new JTextField();
		textdata.setBounds(191, 7, 107, 19);
		contentPane.add(textdata);
		textdata.setColumns(10);
		
		JButton btnenviar = new JButton("Enviar");
		btnenviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				habiliteText();
			}
		});
		btnenviar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Produto obj = new Produto();
				obj.setDescricao(textproduto.getText());
				
				
				GetBanco banco = new GetBanco();
				banco.cadastrarProduto(obj);
				
			}
		});
		btnenviar.setBounds(25, 232, 85, 21);
		contentPane.add(btnenviar);
		desabilitTexfled();
		JButton btneditar = new JButton("Editar");
		btneditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				habiliteText();
			}
		});
		btneditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Produto obj = new Produto();
					obj.setDescricao(textproduto.getText());
					obj.setId(Integer.parseInt(textid.getText()));
					
					GetBanco banco = new GetBanco();
					banco.atualizarProduto(obj);
				
			}
		});
		btneditar.setBounds(134, 232, 85, 21);
		contentPane.add(btneditar);
		
		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto obj = new Produto();
				obj.setId(Integer.parseInt(textid.getText()));
				
				GetBanco banco = new GetBanco();
				banco.deletarProduto(obj);
			}
		});
		btnexcluir.setBounds(240, 232, 85, 21);
		contentPane.add(btnexcluir);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampo();
				desabilitTexfled();
			}
		});
		btnlimpar.setBounds(335, 232, 85, 21);
		contentPane.add(btnlimpar);
		
		tableProduto = new JTable();
		tableProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ExibitionTable();
			}
		});
		tableProduto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Descri\u00E7\u00E3o", "Data de Cadastro"
			}
		));
		tableProduto.getColumnModel().getColumn(2).setPreferredWidth(101);
		tableProduto.setBounds(10, 107, 443, 101);
		contentPane.add(tableProduto);
	}
}
