package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.model.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDAO implements GenericDAO<Cliente> {
	@Override
	public void salvar(Cliente o) throws SQLException {
		String sql = "INSERT INTO cliente(nome,email,senha,telefone,cpf,endereco,pet_shop_id) VALUES(?,?,?,?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, false);
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(Cliente o) throws SQLException {
		String sql = "UPDATE cliente SET nome=?,email=?,senha=?,telefone=?,cpf=?,endereco=?,pet_shop_id=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, true);
			ps.executeUpdate();
		}
	}

	private void fill(PreparedStatement ps, Cliente o, boolean id) throws SQLException {
		ps.setString(1, o.getNome());
		ps.setString(2, o.getEmail());
		ps.setString(3, o.getSenha());
		ps.setString(4, o.getTelefone());
		ps.setString(5, o.getCpf());
		ps.setString(6, o.getEndereco());
		ps.setInt(7, o.getPetShopId());
		if (id)
			ps.setInt(8, o.getId());
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM cliente WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public Cliente buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM cliente WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<Cliente> listarTodos() throws SQLException {
		List<Cliente> l = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM cliente ORDER BY nome");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				l.add(map(rs));
		}
		return l;
	}

	private Cliente map(ResultSet rs) throws SQLException {
		return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"),
				rs.getString("telefone"), rs.getString("cpf"), rs.getString("endereco"), rs.getInt("pet_shop_id"));
	}
}
