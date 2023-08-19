package schooljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import Classes.Aluno;
import Classes.Aluno_Disciplina;

public class AlunoDAO {
	private static String DB_URL = "jdbc:mysql://localhost/fbd";
	private static String DB_USER = "root";
	private static String DB_PASSWORD = "";
	int id = 0;

	void addStudent(Aluno student) {
		try {
			// Class.forName("com.mysql.jdbc.Driver");

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sqlCommand = "INSERT INTO aluno (nome, email, telefone, data_nasc, sexo) VALUES (?,?,?,?,?)";
			stmt = conn.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, student.getNome());
			stmt.setString(2, student.getEmail());
			stmt.setString(3, student.getTelefone());
			stmt.setDate(4, student.getData_nasc());
			stmt.setBoolean(5, student.isSexo());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso!\nMatricula: " + id);
			conn.close();
		} catch (Exception e) {
		}
	}

	List<Aluno> getStudents() {
		int verifica = 0;
		//SELECT * from alunos_info
				
		String sql = "SELECT a.*, AVG(nota)"
				+ " FROM aluno a JOIN aluno_disciplina ad ON a.matricula = ad.aluno_matr"
				+ " GROUP BY matricula;";
		List<Aluno> students = new ArrayList<Aluno>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Matrícula: " + rs.getInt(1) + ". Nome: " + rs.getString(2) + ". Email: "
						+ rs.getString(3) + ". Telefone: " + rs.getString(4) + ". \nData de Nascimento: " + rs.getDate(5)
					+ ". Sexo: " + rs.getBoolean(6) + ". Média: " + rs.getFloat(8)
					+ ". Distincao: " + rs.getString(7));
				verifica++;
			}
			if (verifica == 0) {
				System.out.println("Lista vazia!");
			}
			conn.close();
			System.out.println("");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return students;
	}

	void updateStudent(Aluno student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sqlCommand = "update aluno set nome=?, email=?, telefone=?, data_nasc=?, sexo=? where matricula=?";
			stmt = conn.prepareStatement(sqlCommand);
			stmt.setString(1, student.getNome());
			stmt.setString(2, student.getEmail());
			stmt.setString(3, student.getTelefone());
			stmt.setDate(4, student.getData_nasc());
			stmt.setBoolean(5, student.isSexo());
			stmt.setFloat(6, student.getMatricula());
			int verifica = stmt.executeUpdate();
			if (verifica > 0) {
				JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "Matrícula inválida");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void deleteStudent(Aluno student) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sqlCommand = "delete from aluno where matricula=?";
			stmt = conn.prepareStatement(sqlCommand);
			stmt.setLong(1, student.getMatricula());
			int verifica = stmt.executeUpdate();
			if (verifica > 0) {
				JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Matrícula inválida.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void adicionarResultado(Aluno_Disciplina aludisc) {
		try {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sqlCommand = "insert into aluno_disciplina (aluno_matr, disciplina_cod, cod_periodo, nota, frequencia) values (?,?,?,?,?)";
			stmt = conn.prepareStatement(sqlCommand);
			stmt.setInt(1, aludisc.getAluno_matr());
			stmt.setInt(2, aludisc.getDisciplina_cod());
			stmt.setString(3, aludisc.getPeriodo());
			stmt.setFloat(4, aludisc.getNota());
			stmt.setFloat(5, aludisc.getFrequencia());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Resultado adicionado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void buscaAluno(String sub) {
		int aux = 0;
		try {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sqlCommand = "SELECT * FROM aluno WHERE nome LIKE ? OR email LIKE ?";
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.prepareStatement(sqlCommand);
			String submin = sub.toLowerCase();
			stmt.setString(1, "%" + submin + "%");
			stmt.setString(2, "%" + submin + "%");
			rs = stmt.executeQuery();
			// Criar a consulta SQL com o parâmetro da substring
			// Definir o parâmetro da substring (ignorando maiúsculas e minúsculas)

			while (rs.next()) {
				System.out.println(
						"Matricula: " + rs.getInt(1) + ". Nome: " + rs.getString(2) + ". Email: " + rs.getString(3));
				aux = 1;
			}
			if (aux == 0) {
				System.out.println("A substring não foi encontrada em nenhum registro.");
			}
			System.out.println("\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void listaDisciplinas(int matricula) {
		try {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sqlCommand = "SELECT d.nome, a.cod_periodo, a.nota, a.frequencia"
					+ " FROM aluno_disciplina a JOIN disciplina d" + " ON  disciplina_cod = codigo"
					+ " WHERE a.aluno_matr = ?";
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.prepareStatement(sqlCommand);
			stmt.setInt(1, matricula);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("Disciplina: " + rs.getString(1) + ". Periodo: " + rs.getString(2) + ". Nota: "
						+ rs.getFloat(3) + ". Frequencia: " + rs.getFloat(4) + "%");
			}
			System.out.println("\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
