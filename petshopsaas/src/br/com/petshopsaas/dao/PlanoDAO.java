package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.model.Plano;
import java.sql.*;
import java.util.*;

public class PlanoDAO implements GenericDAO<Plano> {
	@Override
	public void salvar(Plano o) throws SQLException {
		String sql = "INSERT INTO plano(nome,descricao,valor_mensal,quantidade_banhos,quantidade_tosas,ativo,pet_shop_id) VALUES(?,?,?,?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, false);
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(Plano o) throws SQLException {
		String sql = "UPDATE plano SET nome=?,descricao=?,valor_mensal=?,quantidade_banhos=?,quantidade_tosas=?,ativo=?,pet_shop_id=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, true);
			ps.executeUpdate();
		}
	}

	private void fill(PreparedStatement ps, Plano o, boolean id) throws SQLException {
		ps.setString(1, o.getNome());
		ps.setString(2, o.getDescricao());
		ps.setDouble(3, o.getValorMensal());
		ps.setInt(4, o.getQuantidadeBanhos());
		ps.setInt(5, o.getQuantidadeTosas());
		ps.setBoolean(6, o.isAtivo());
		ps.setInt(7, o.getPetShopId());
		if (id)
			ps.setInt(8, o.getId());
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM plano WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public Plano buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM plano WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<Plano> listarTodos() throws SQLException {
		List<Plano> l = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM plano ORDER BY nome");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				l.add(map(rs));
		}
		return l;
	}

	private Plano map(ResultSet rs) throws SQLException {
		return new Plano(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), rs.getDouble("valor_mensal"),
				rs.getInt("quantidade_banhos"), rs.getInt("quantidade_tosas"), rs.getBoolean("ativo"),
				rs.getInt("pet_shop_id"));
	}
}
