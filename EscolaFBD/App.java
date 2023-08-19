package schooljdbc;

import java.awt.Button;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Classes.Aluno;
import Classes.Aluno_Disciplina;
import Classes.Disciplina;

public class App extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtNomeUpdate;
	private JTextField txtEmailUpdate;
	private JTextField txtId;
	private JTextField txtIdDel;
	private JTextField txtNomeDis;
	private JTextField txtCred;
	private JTextField txtCodDisc;
	private JTextField txtCodDiscAtu;
	private JTextField txtNomeUpdateDisc;
	private JTextField txtCredUpdate;
	private JTextField txtCodDeleteDisc;
	private JTextField txtBuscar;
	private JTextField txtTelefone;
	private JTextField txtNasc;
	private JTextField txtSexo;
	private JTextField txtSexoUpdate;
	private JTextField txtTelUpdate;
	private JTextField txtDataUpdate;
	private JTextField txtAlunoMatr;
	private JTextField txtPeriodo;
	private JTextField txtNota;
	private JTextField txtFrequencia;
	private JLabel lblCodDiscDel;
	private JTextField txtPeriodoTur;
	private JTextField txtCodDiscTur;
	private JTextField txtHistorico;
	private JPanel listarAlunosDisc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setBackground(new Color(192, 192, 192));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 367);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane abaMaior = new JTabbedPane(JTabbedPane.TOP);
		abaMaior.setBounds(0, 0, 534, 328);
		abaMaior.setBackground(new Color(255, 255, 255));
		contentPane.add(abaMaior);

		JTabbedPane aba1 = new JTabbedPane(JTabbedPane.TOP);
		aba1.setBackground(new Color(255, 255, 255));
		aba1.setBorder(null);
		aba1.setBounds(0, 34, 406, 243);
		// contentPane.add(aba);
		abaMaior.addTab("Aluno", aba1);

		JPanel inserir = new JPanel();
		inserir.setBackground(new Color(255, 255, 255));
		inserir.setBounds(0, 0, 44, 10);
		aba1.addTab("Adicionar", inserir);
		inserir.setLayout(null);

		Button btnAdd = new Button("Adicionar");
		btnAdd.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Aluno aluno = new Aluno();
				aluno.setNome(txtNome.getText());
				aluno.setEmail(txtEmail.getText());
				aluno.setTelefone(txtTelefone.getText());

				String texto = txtSexo.getText();
				boolean sexo = false;
				if (texto.equals("0")) {
					sexo = false;
				} else if (texto.equals("1")) {
					sexo = true;
				} else {
					JOptionPane.showMessageDialog(null, "Sexo inválido. Tente inserir 0 ou 1");
				}
				aluno.setSexo(sexo);

				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String coe = txtNasc.getText();
				Date data = null;
				try {
					data = formato.parse(coe);
					aluno.setData_nasc(new java.sql.Date(data.getTime()));
				} catch (ParseException e1) {
					Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e1);
				}

				if (txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTelefone.getText().isEmpty()
						|| txtNasc.getText().isEmpty() || txtSexo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
				} else {
					AlunoDAO teste = new AlunoDAO();
					teste.addStudent(aluno);
					txtNome.setText(null);
					txtEmail.setText(null);
					txtSexo.setText(null);
					txtTelefone.setText(null);
					txtNasc.setText(null);
				}
			}
		});
		btnAdd.setBounds(187, 226, 112, 22);
		inserir.add(btnAdd);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(69, 37, 46, 14);
		inserir.add(lblNome);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(69, 87, 46, 14);
		inserir.add(lblEmail);

		txtNome = new JTextField();
		txtNome.setBounds(69, 56, 141, 20);
		inserir.add(txtNome);
		txtNome.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(69, 112, 141, 20);
		inserir.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblTel = new JLabel("Telefone:");
		lblTel.setBounds(69, 148, 76, 14);
		inserir.add(lblTel);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(69, 173, 141, 20);
		inserir.add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblNasc = new JLabel("Data de Nascimento:");
		lblNasc.setBounds(274, 37, 131, 14);
		inserir.add(lblNasc);

		txtNasc = new JTextField();
		txtNasc.setBounds(274, 56, 121, 20);
		inserir.add(txtNasc);
		txtNasc.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo: 0 para M e 1 para F");
		lblSexo.setBounds(274, 87, 187, 14);
		inserir.add(lblSexo);

		txtSexo = new JTextField();
		txtSexo.setBounds(274, 112, 86, 20);
		inserir.add(txtSexo);
		txtSexo.setColumns(10);

		JPanel listar = new JPanel();
		listar.setBackground(new Color(255, 255, 255));
		listar.setToolTipText("Listar");
		aba1.addTab("Listar", listar);
		listar.setLayout(null);

		JLabel lblListar = new JLabel("Clique para listar os alunos: ");
		lblListar.setHorizontalAlignment(SwingConstants.CENTER);
		lblListar.setBounds(159, 72, 177, 14);
		listar.add(lblListar);

		Button btnListar = new Button("Listar");
		btnListar.setForeground(new Color(0, 0, 0));
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlunoDAO teste = new AlunoDAO();
				teste.getStudents();
			}
		});
		btnListar.setBounds(205, 104, 98, 22);
		listar.add(btnListar);

		JPanel atualizar = new JPanel();
		atualizar.setBackground(new Color(255, 255, 255));
		aba1.addTab("Atualizar", atualizar);
		atualizar.setLayout(null);

		JLabel lblMatrUpdate = new JLabel("Informe a matrícula do aluno que deseja atualizar:");
		lblMatrUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatrUpdate.setBounds(57, 25, 358, 14);
		atualizar.add(lblMatrUpdate);

		JLabel lblNomeUpdate = new JLabel("Nome:");
		lblNomeUpdate.setBounds(20, 123, 81, 14);
		atualizar.add(lblNomeUpdate);

		JLabel lblEmailUpdate = new JLabel("Email:");
		lblEmailUpdate.setBounds(20, 176, 81, 14);
		atualizar.add(lblEmailUpdate);

		txtId = new JTextField();
		txtId.setBounds(182, 50, 120, 20);
		atualizar.add(txtId);
		txtId.setColumns(10);

		txtNomeUpdate = new JTextField();
		txtNomeUpdate.setBounds(20, 145, 143, 20);
		atualizar.add(txtNomeUpdate);
		txtNomeUpdate.setColumns(10);

		txtEmailUpdate = new JTextField();
		txtEmailUpdate.setBounds(20, 201, 143, 20);
		atualizar.add(txtEmailUpdate);
		txtEmailUpdate.setColumns(10);

		JButton btnUpdate = new JButton("Atualizar");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno aluno = new Aluno();
				aluno.setNome(txtNomeUpdate.getText());
				aluno.setEmail(txtEmailUpdate.getText());
				aluno.setTelefone(txtTelUpdate.getText());

				String texto = txtSexoUpdate.getText();
				boolean sexo = false;
				if (texto.equals("0")) {
					sexo = false;
				} else if (texto.equals("1")) {
					sexo = true;
				} else {
					JOptionPane.showMessageDialog(null, "Sexo inválido. Tente inserir 0 ou 1");
				}
				aluno.setSexo(sexo);

				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String coe = txtDataUpdate.getText();
				Date data = null;
				try {
					data = formato.parse(coe);
					aluno.setData_nasc(new java.sql.Date(data.getTime()));
				} catch (ParseException e1) {
					Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e1);
				}
				// java.sql.Date sqlDate = new java.sql.Date(data.getTime());

				String aux = txtId.getText();
				if (txtId.getText().isEmpty()) {
					int id = 0;
					aluno.setMatricula(id);
				} else {
					int id = Integer.parseInt(aux);
					aluno.setMatricula(id);
				}
				if (txtNomeUpdate.getText().isEmpty() || txtEmailUpdate.getText().isEmpty()
						|| txtId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
				} else {
					AlunoDAO teste = new AlunoDAO();
					teste.updateStudent(aluno);
					txtId.setText("");
					txtNomeUpdate.setText("");
					txtEmailUpdate.setText("");
					txtSexoUpdate.setText("");
					txtTelUpdate.setText("");
					txtDataUpdate.setText("");
				}
			}
		});
		btnUpdate.setBounds(330, 200, 112, 23);
		atualizar.add(btnUpdate);

		JLabel lblTelUpdate = new JLabel("Telefone:");
		lblTelUpdate.setBounds(188, 123, 130, 14);
		atualizar.add(lblTelUpdate);

		JLabel lblSexoUpdate = new JLabel("Sexo: 0 para M e 1 para F");
		lblSexoUpdate.setBounds(188, 176, 185, 14);
		atualizar.add(lblSexoUpdate);

		JLabel lblDataUpdate = new JLabel("Data de Nascimento:");
		lblDataUpdate.setBounds(328, 123, 143, 14);
		atualizar.add(lblDataUpdate);

		txtSexoUpdate = new JTextField();
		txtSexoUpdate.setBounds(188, 201, 114, 20);
		atualizar.add(txtSexoUpdate);
		txtSexoUpdate.setColumns(10);

		txtTelUpdate = new JTextField();
		txtTelUpdate.setColumns(10);
		txtTelUpdate.setBounds(188, 145, 114, 20);
		atualizar.add(txtTelUpdate);

		txtDataUpdate = new JTextField();
		txtDataUpdate.setColumns(10);
		txtDataUpdate.setBounds(328, 145, 114, 20);
		atualizar.add(txtDataUpdate);

		JPanel remover = new JPanel();
		remover.setBorder(null);
		remover.setBackground(new Color(255, 255, 255));
		aba1.addTab("Remover", remover);
		remover.setLayout(null);

		JLabel lblMatrDel = new JLabel("Informe a matrícula do aluno que deseja deletar:");
		lblMatrDel.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatrDel.setBounds(99, 46, 287, 14);
		remover.add(lblMatrDel);

		txtIdDel = new JTextField();
		txtIdDel.setBounds(190, 83, 98, 20);
		remover.add(txtIdDel);
		txtIdDel.setColumns(10);

		Button btnDelete = new Button("Deletar");
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Aluno aluno = new Aluno();
				String aux = txtIdDel.getText();
				if (txtIdDel.getText().isEmpty()) {
					int id = 0;
					aluno.setMatricula(id);
				} else {
					int id = Integer.parseInt(aux);
					aluno.setMatricula(id);
				}
				AlunoDAO teste = new AlunoDAO();
				teste.deleteStudent(aluno);
				txtIdDel.setText("");
			}
		});
		btnDelete.setBounds(190, 155, 98, 22);
		remover.add(btnDelete);

		JPanel buscar = new JPanel();
		buscar.setBackground(new Color(255, 255, 255));
		aba1.addTab("Buscar", null, buscar, null);
		buscar.setLayout(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(161, 96, 141, 20);
		buscar.add(txtBuscar);
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlunoDAO teste = new AlunoDAO();
				teste.buscaAluno(txtBuscar.getText());
				txtIdDel.setText("");
			}
		});
		btnBuscar.setBounds(185, 151, 89, 23);
		buscar.add(btnBuscar);

		JLabel lblBuscar = new JLabel("Insira a busca abaixo:");
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setBounds(135, 65, 200, 20);
		buscar.add(lblBuscar);

		JPanel resultado = new JPanel();
		resultado.setBackground(new Color(255, 255, 255));
		aba1.addTab("Resultado", null, resultado, null);
		resultado.setLayout(null);

		txtAlunoMatr = new JTextField();
		txtAlunoMatr.setBounds(77, 56, 123, 20);
		resultado.add(txtAlunoMatr);
		txtAlunoMatr.setColumns(10);

		JButton btnTeste = new JButton("Adicionar");
		btnTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAlunoMatr.getText().isEmpty() || txtCodDisc.getText().isEmpty() || txtPeriodo.getText().isEmpty()
						|| txtNota.getText().isEmpty() || txtFrequencia.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
				} else {
					Aluno_Disciplina aludisc = new Aluno_Disciplina();
					String aux = txtAlunoMatr.getText();
					aludisc.setAluno_matr(Integer.parseInt(aux));

					aux = txtCodDisc.getText();
					aludisc.setDisciplina_cod(Integer.parseInt(aux));

					aludisc.setPeriodo(txtPeriodo.getText());

					aux = txtNota.getText();
					aludisc.setNota(Float.parseFloat(aux));

					aux = txtFrequencia.getText();
					aludisc.setFrequencia(Float.parseFloat(aux));

					AlunoDAO teste = new AlunoDAO();
					teste.adicionarResultado(aludisc);
					txtAlunoMatr.setText(null);
					txtCodDisc.setText(null);
					txtPeriodo.setText(null);
					txtNota.setText(null);
					txtFrequencia.setText(null);
				}
			}
		});
		btnTeste.setBounds(272, 191, 112, 21);
		resultado.add(btnTeste);

		JLabel lblAlunoMatr = new JLabel("Matricula do Aluno:");
		lblAlunoMatr.setBounds(77, 31, 160, 14);
		resultado.add(lblAlunoMatr);

		txtCodDisc = new JTextField();
		txtCodDisc.setColumns(10);
		txtCodDisc.setBounds(77, 120, 123, 20);
		resultado.add(txtCodDisc);

		txtPeriodo = new JTextField();
		txtPeriodo.setColumns(10);
		txtPeriodo.setBounds(77, 191, 123, 20);
		resultado.add(txtPeriodo);

		txtNota = new JTextField();
		txtNota.setColumns(10);
		txtNota.setBounds(272, 120, 112, 20);
		resultado.add(txtNota);

		txtFrequencia = new JTextField();
		txtFrequencia.setColumns(10);
		txtFrequencia.setBounds(272, 56, 112, 20);
		resultado.add(txtFrequencia);

		JLabel lblNota = new JLabel("Nota:");
		lblNota.setBounds(272, 95, 43, 14);
		resultado.add(lblNota);

		JLabel lblFrequencia = new JLabel("Frequência:");
		lblFrequencia.setBounds(272, 31, 86, 14);
		resultado.add(lblFrequencia);

		JLabel lblCodDisc = new JLabel("Código da disciplina: ");
		lblCodDisc.setBounds(77, 95, 185, 14);
		resultado.add(lblCodDisc);

		JLabel lblCodPeriodo = new JLabel("Código do Período: ");
		lblCodPeriodo.setBounds(77, 166, 160, 14);
		resultado.add(lblCodPeriodo);
		
		
		JPanel historico = new JPanel();
		historico.setBackground(new Color(255, 255, 255));
		historico.setBounds(0, 0, 44, 10);
		aba1.addTab("Histórico", historico);
		historico.setLayout(null);
		
		JButton btnHistorico = new JButton("Histórico");
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtHistorico.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
				} else {
				int aux = Integer.parseInt(txtHistorico.getText());
				AlunoDAO chama = new AlunoDAO();
				chama.listaDisciplinas(aux);
				txtHistorico.setText(null);
				}
			}
		});
		btnHistorico.setBounds(213, 163, 85, 21);
		historico.add(btnHistorico);
		
		JLabel lblHistorico = new JLabel("Digite a matrícula do aluno que deseja ver o histórico:");
		lblHistorico.setBounds(102, 42, 318, 13);
		historico.add(lblHistorico);
		
		txtHistorico = new JTextField();
		txtHistorico.setBounds(200, 85, 109, 19);
		historico.add(txtHistorico);
		txtHistorico.setColumns(10);

		JTabbedPane aba2 = new JTabbedPane(JTabbedPane.TOP);
		aba2.setBackground(new Color(255, 255, 255));
		abaMaior.addTab("Disciplina", aba2);

		JPanel addDisc = new JPanel();
		addDisc.setBackground(new Color(255, 255, 255));
		aba2.addTab("Adicionar", null, addDisc, null);
		addDisc.setLayout(null);

		txtNomeDis = new JTextField();
		txtNomeDis.setBounds(146, 67, 183, 20);
		addDisc.add(txtNomeDis);
		txtNomeDis.setColumns(10);

		txtCred = new JTextField();
		txtCred.setBounds(194, 129, 86, 20);
		addDisc.add(txtCred);
		txtCred.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Insira o nome da disciplina:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(156, 42, 165, 14);
		addDisc.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Insira os créditos:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(146, 104, 175, 14);
		addDisc.add(lblNewLabel_6);

		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disciplina disc = new Disciplina();
				disc.setNome(txtNomeDis.getText());
				int aux = Integer.parseInt(txtCred.getText());
				disc.setCreditos(aux);
				if (txtNomeDis.getText().isEmpty() || txtCred.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
				} else {
					DisciplinaDAO teste = new DisciplinaDAO();
					teste.addDisciplina(disc);
					txtNomeDis.setText(null);
					txtCred.setText(null);
				}
			}
		});
		btnNewButton.setBounds(190, 178, 89, 23);
		addDisc.add(btnNewButton);

		JPanel listarAlunosDisc;
		listarAlunosDisc = new JPanel();
		listarAlunosDisc.setBackground(new Color(255, 255, 255));
		aba2.addTab("Listar", null, listarAlunosDisc, null);
		listarAlunosDisc.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Clique para listar as disciplinas: ");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(132, 61, 224, 14);
		listarAlunosDisc.add(lblNewLabel_7);

		Button btnListarDisc = new Button("Listar");
		btnListarDisc.setForeground(new Color(0, 0, 0));
		btnListarDisc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnListarDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisciplinaDAO teste = new DisciplinaDAO();
				teste.getDisciplina();
			}
		});
		btnListarDisc.setBounds(204, 93, 84, 22);
		listarAlunosDisc.add(btnListarDisc);

		JPanel atualizarDisc = new JPanel();
		atualizarDisc.setBackground(new Color(255, 255, 255));
		aba2.addTab("Atualizar", null, atualizarDisc, null);
		atualizarDisc.setLayout(null);

		JLabel lblIdDisc = new JLabel("Informe o matrícula da disciplina que deseja atualizar:");
		lblIdDisc.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdDisc.setBounds(41, 28, 390, 14);
		atualizarDisc.add(lblIdDisc);

		JLabel lblNomeDisc = new JLabel("Nome:");
		lblNomeDisc.setBounds(41, 143, 191, 14);
		atualizarDisc.add(lblNomeDisc);

		JLabel lblCredDisc = new JLabel("Créditos:");
		lblCredDisc.setBounds(299, 143, 105, 14);
		atualizarDisc.add(lblCredDisc);

		txtCodDiscAtu = new JTextField();
		txtCodDiscAtu.setBounds(181, 55, 105, 20);
		atualizarDisc.add(txtCodDiscAtu);
		txtCodDiscAtu.setColumns(10);

		txtNomeUpdateDisc = new JTextField();
		txtNomeUpdateDisc.setBounds(41, 162, 203, 20);
		atualizarDisc.add(txtNomeUpdateDisc);
		txtNomeUpdateDisc.setColumns(10);

		txtCredUpdate = new JTextField();
		txtCredUpdate.setBounds(299, 162, 105, 20);
		atualizarDisc.add(txtCredUpdate);
		txtCredUpdate.setColumns(10);

		JButton btnUpdateDisc = new JButton("Atualizar");
		btnUpdateDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disciplina disc = new Disciplina();
				disc.setNome(txtNomeUpdateDisc.getText());
				int cred = Integer.parseInt(txtCredUpdate.getText());
				disc.setCreditos(cred);
				String aux = txtCodDiscAtu.getText();
				if (txtCodDiscAtu.getText().isEmpty()) {
					int id = 0;
					disc.setCodigo(id);
				} else {
					int id = Integer.parseInt(aux);
					disc.setCodigo(id);
				}
				if (txtNomeUpdateDisc.getText().isEmpty() || txtCredUpdate.getText().isEmpty()
						|| txtCodDiscAtu.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
				} else {
					DisciplinaDAO teste = new DisciplinaDAO();
					teste.updateDisciplina(disc);
					txtCodDiscAtu.setText("");
					txtCredUpdate.setText("");
					txtNomeUpdateDisc.setText("");
				}
			}
		});
		btnUpdateDisc.setBounds(181, 217, 105, 23);
		atualizarDisc.add(btnUpdateDisc);

		JLabel lblNewLabel_14 = new JLabel("Insira as novas informações:");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setBounds(65, 118, 340, 14);
		atualizarDisc.add(lblNewLabel_14);

		JPanel removerDisc = new JPanel();
		removerDisc.setBackground(new Color(255, 255, 255));
		aba2.addTab("Remover", null, removerDisc, null);
		removerDisc.setLayout(null);

		JLabel lblCodDiscDel = new JLabel("Informe o codigo da disciplina que deseja deletar:");
		lblCodDiscDel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodDiscDel.setBounds(104, 46, 287, 14);
		removerDisc.add(lblCodDiscDel);

		txtCodDeleteDisc = new JTextField();
		txtCodDeleteDisc.setBounds(195, 83, 98, 20);
		removerDisc.add(txtCodDeleteDisc);
		txtCodDeleteDisc.setColumns(10);

		Button btnDeleteDisc = new Button("Deletar");
		btnDeleteDisc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Disciplina disc = new Disciplina();
				String aux = txtCodDeleteDisc.getText();
				if (txtCodDeleteDisc.getText().isEmpty()) {
					int id = 0;
					disc.setCodigo(id);
				} else {
					int id = Integer.parseInt(aux);
					disc.setCodigo(id);
				}
				DisciplinaDAO teste = new DisciplinaDAO();
				teste.deteteDisciplina(disc);
				txtCodDeleteDisc.setText("");
			}
		});
		btnDeleteDisc.setBounds(195, 155, 98, 22);
		removerDisc.add(btnDeleteDisc);

		JPanel turma = new JPanel();
		turma.setBackground(new Color(255, 255, 255));
		aba2.addTab("Turma", null, turma, null);

		JButton btnTurma = new JButton("Ver Turma");
		btnTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String periodo = txtPeriodoTur.getText();
				String codigo = txtCodDiscTur.getText();
				DisciplinaDAO teste = new DisciplinaDAO();
				teste.exibirTurma(Integer.parseInt(codigo), periodo);
				txtCodDiscTur.setText(null);
				txtPeriodoTur.setText(null);
			}
		});
		turma.setLayout(null);
		btnTurma.setBounds(179, 183, 107, 23);
		turma.add(btnTurma);

		txtPeriodoTur = new JTextField();
		txtPeriodoTur.setBounds(168, 127, 129, 20);
		turma.add(txtPeriodoTur);
		txtPeriodoTur.setColumns(10);

		txtCodDiscTur = new JTextField();
		txtCodDiscTur.setText("");
		txtCodDiscTur.setBounds(168, 73, 129, 20);
		turma.add(txtCodDiscTur);
		txtCodDiscTur.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Insira o Código da disciplina: ");
		lblNewLabel_12.setBounds(167, 45, 224, 14);
		turma.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Período:");
		lblNewLabel_13.setBounds(168, 104, 118, 14);
		turma.add(lblNewLabel_13);
		
		JPanel listarDisc = new JPanel();
		listarDisc.setBackground(new Color(255, 255, 255));
		aba2.addTab("Exibir", null, listarDisc, null);
		listarDisc.setLayout(null);
		
		JLabel lblListarDisc = new JLabel("Exibe os dados da turma: ");
		lblListarDisc.setBounds(182, 65, 164, 13);
		listarDisc.add(lblListarDisc);
		
		JButton btnNewButton_1 = new JButton("Listar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisciplinaDAO chama = new DisciplinaDAO();
				chama.listarDisciplina();
			}
		});
		btnNewButton_1.setBounds(207, 133, 85, 21);
		listarDisc.add(btnNewButton_1);

	}
}
