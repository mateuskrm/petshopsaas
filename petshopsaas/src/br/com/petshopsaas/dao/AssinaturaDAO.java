package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.enums.StatusAssinatura;
import br.com.petshopsaas.model.Assinatura;
import java.sql.*;
import java.util.*;

public class AssinaturaDAO implements GenericDAO<Assinatura> {
	@Override
	public void salvar(Assinatura o) throws SQLException {
		String sql = "INSERT INTO assinatura(cliente_id,plano_id,data_inicio,data_fim,status,banhos_restantes,tosas_restantes) VALUES(?,?,?,?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, false);
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(Assinatura o) throws SQLException {
		String sql = "UPDATE assinatura SET cliente_id=?,plano_id=?,data_inicio=?,data_fim=?,status=?,banhos_restantes=?,tosas_restantes=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, true);
			ps.executeUpdate();
		}
	}

	private void fill(PreparedStatement ps, Assinatura o, boolean id) throws SQLException {
		ps.setInt(1, o.getClienteId());
		ps.setInt(2, o.getPlanoId());
		ps.setDate(3, DAOUtils.toSqlDate(o.getDataInicio()));
		ps.setDate(4, DAOUtils.toSqlDate(o.getDataFim()));
		ps.setString(5, o.getStatus().name());
		ps.setInt(6, o.getBanhosRestantes());
		ps.setInt(7, o.getTosasRestantes());
		if (id)
			ps.setInt(8, o.getId());
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM assinatura WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public Assinatura buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM assinatura WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<Assinatura> listarTodos() throws SQLException {
		List<Assinatura> l = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM assinatura ORDER BY id DESC");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				l.add(map(rs));
		}
		return l;
	}

	private Assinatura map(ResultSet rs) throws SQLException {
		return new Assinatura(rs.getInt("id"), rs.getInt("cliente_id"), rs.getInt("plano_id"),
				DAOUtils.toLocalDate(rs.getDate("data_inicio")), DAOUtils.toLocalDate(rs.getDate("data_fim")),
				StatusAssinatura.valueOf(rs.getString("status")), rs.getInt("banhos_restantes"),
				rs.getInt("tosas_restantes"));
	}
}
