package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.enums.EspeciePet;
import br.com.petshopsaas.model.Pet;
import java.sql.*;
import java.util.*;

public class PetDAO implements GenericDAO<Pet> {
	@Override
	public void salvar(Pet o) throws SQLException {
		String sql = "INSERT INTO pet(nome,especie,raca,idade,peso,observacoes,cliente_id) VALUES(?,?,?,?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, false);
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(Pet o) throws SQLException {
		String sql = "UPDATE pet SET nome=?,especie=?,raca=?,idade=?,peso=?,observacoes=?,cliente_id=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, true);
			ps.executeUpdate();
		}
	}

	private void fill(PreparedStatement ps, Pet o, boolean id) throws SQLException {
		ps.setString(1, o.getNome());
		ps.setString(2, o.getEspecie().name());
		ps.setString(3, o.getRaca());
		ps.setInt(4, o.getIdade());
		ps.setDouble(5, o.getPeso());
		ps.setString(6, o.getObservacoes());
		ps.setInt(7, o.getClienteId());
		if (id)
			ps.setInt(8, o.getId());
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM pet WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public Pet buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM pet WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<Pet> listarTodos() throws SQLException {
		List<Pet> l = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM pet ORDER BY nome");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				l.add(map(rs));
		}
		return l;
	}

	private Pet map(ResultSet rs) throws SQLException {
		return new Pet(rs.getInt("id"), rs.getString("nome"), EspeciePet.valueOf(rs.getString("especie")),
				rs.getString("raca"), rs.getInt("idade"), rs.getDouble("peso"), rs.getString("observacoes"),
				rs.getInt("cliente_id"));
	}
}
