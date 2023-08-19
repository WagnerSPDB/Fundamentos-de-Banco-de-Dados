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

import Classes.Disciplina;

public class DisciplinaDAO {
	private static String DB_URL = "jdbc:mysql://localhost/fbd";
	private static String DB_USER = "root";
	private static String DB_PASSWORD = "";
	int cod = 0;

	// adicionar disciplina
	void addDisciplina(Disciplina disciplina) {
		try {
			// Class.forName("com.mysql.jdbc.Driver");

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			String sqlCommand = "INSERT INTO disciplina (nome, creditos) VALUES (?,?)";
			stmt = conn.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, disciplina.getNome());
			stmt.setInt(2, disciplina.getCreditos());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				cod = rs.getInt(1);
			}
			JOptionPane.showMessageDialog(null, "Disciplina inserida com sucesso!\nCódigo: " + cod);
			conn.close();
		} catch (Exception e) {
		}
	}

	// Listar disciplinas
	List<Disciplina> getDisciplina() {
		int verifica = 0;
		String sql = "SELECT * FROM disciplina";
		List<Disciplina> disciplina = new ArrayList<Disciplina>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"Código: " + rs.getInt(1) + ". Nome: " + rs.getString(2) + ". Créditos: " + rs.getString(3));
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
		return disciplina;
	}

	// Atualizar disciplinas
	void updateDisciplina(Disciplina disciplina) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sqlCommand = "update disciplina set nome=?, creditos=? where codigo=?";
			stmt = conn.prepareStatement(sqlCommand);
			stmt.setString(1, disciplina.getNome());
			stmt.setInt(2, disciplina.getCreditos());
			stmt.setInt(3, disciplina.getCodigo());

			int verifica = stmt.executeUpdate();
			if (verifica > 0) {
				JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "Cógido inválido");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Deletar disciplina
	void deteteDisciplina(Disciplina disciplina) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sqlCommand = "delete from disciplina where codigo=?";
			stmt = conn.prepareStatement(sqlCommand);
			stmt.setInt(1, disciplina.getCodigo());
			int verifica = stmt.executeUpdate();
			if (verifica > 0) {
				JOptionPane.showMessageDialog(null, "Disciplina removida com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Código inválido.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void exibirTurma(int cod_disciplina, String periodo) {
		try {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sqlCommand = "SELECT a.matricula, a.nome, ad.nota, ad.frequencia"
					+ " FROM aluno a JOIN aluno_disciplina ad" + " ON matricula = aluno_matr"
					+ " WHERE ad.disciplina_cod  = ? AND ad.cod_periodo = ?";
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.prepareStatement(sqlCommand);
			stmt.setInt(1, cod_disciplina);
			stmt.setString(2, periodo);
			rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println("Matricula: " + rs.getString(1) + ". Aluno: " + rs.getString(2) + ". Nota: "
						+ rs.getFloat(3) + ". Frequencia: " + rs.getFloat(4) + "%");
			}
			System.out.println("\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	List<Disciplina> listarDisciplina() {
		int verifica = 0;
				/*"SELECT d.nome, ad.cod_periodo, COUNT(*), MAX(nota), MIN(nota), AVG(nota)"
				+ " FROM disciplina d JOIN aluno_disciplina ad ON d.codigo = ad.disciplina_cod"
				+ " GROUP BY ad.disciplina_cod, ad.cod_periodo;";*/
		String sql = "SELECT * FROM turmas_info";
		List<Disciplina> disciplina = new ArrayList<Disciplina>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"Disciplina: " + rs.getString(1) + ". Período: " + rs.getString(2) + ". Qtd Alunos: " + rs.getInt(3) 
						+ ". Maior nota: " + rs.getFloat(4) + ". Menor nota: " + rs.getFloat(5) + ". Média: " + rs.getFloat(6));
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
		return disciplina;
	}
}
