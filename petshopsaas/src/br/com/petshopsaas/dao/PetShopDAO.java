package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.model.PetShop;
import java.sql.*;
import java.util.*;

public class PetShopDAO implements GenericDAO<PetShop> {
	@Override
	public void salvar(PetShop p) throws SQLException {
		String sql = "INSERT INTO pet_shop(nome, cnpj, endereco, telefone) VALUES(?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCnpj());
			ps.setString(3, p.getEndereco());
			ps.setString(4, p.getTelefone());
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(PetShop p) throws SQLException {
		String sql = "UPDATE pet_shop SET nome=?, cnpj=?, endereco=?, telefone=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCnpj());
			ps.setString(3, p.getEndereco());
			ps.setString(4, p.getTelefone());
			ps.setInt(5, p.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM pet_shop WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public PetShop buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM pet_shop WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<PetShop> listarTodos() throws SQLException {
		List<PetShop> lista = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM pet_shop ORDER BY nome");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				lista.add(map(rs));
		}
		return lista;
	}

	private PetShop map(ResultSet rs) throws SQLException {
		return new PetShop(rs.getInt("id"), rs.getString("nome"), rs.getString("cnpj"), rs.getString("endereco"),
				rs.getString("telefone"));
	}
}
