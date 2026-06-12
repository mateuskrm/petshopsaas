package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.model.Funcionario;
import java.sql.*;
import java.util.*;

public class FuncionarioDAO implements GenericDAO<Funcionario> {
	@Override
	public void salvar(Funcionario o) throws SQLException {
		String sql = "INSERT INTO funcionario(nome,email,senha,telefone,cargo,especialidade,pet_shop_id) VALUES(?,?,?,?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, false);
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(Funcionario o) throws SQLException {
		String sql = "UPDATE funcionario SET nome=?,email=?,senha=?,telefone=?,cargo=?,especialidade=?,pet_shop_id=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, true);
			ps.executeUpdate();
		}
	}

	private void fill(PreparedStatement ps, Funcionario o, boolean id) throws SQLException {
		ps.setString(1, o.getNome());
		ps.setString(2, o.getEmail());
		ps.setString(3, o.getSenha());
		ps.setString(4, o.getTelefone());
		ps.setString(5, o.getCargo());
		ps.setString(6, o.getEspecialidade());
		ps.setInt(7, o.getPetShopId());
		if (id)
			ps.setInt(8, o.getId());
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM funcionario WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public Funcionario buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM funcionario WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<Funcionario> listarTodos() throws SQLException {
		List<Funcionario> l = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM funcionario ORDER BY nome");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				l.add(map(rs));
		}
		return l;
	}

	private Funcionario map(ResultSet rs) throws SQLException {
		return new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"),
				rs.getString("telefone"), rs.getString("cargo"), rs.getString("especialidade"),
				rs.getInt("pet_shop_id"));
	}
}
