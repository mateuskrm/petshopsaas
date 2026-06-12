package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.enums.StatusPagamento;
import br.com.petshopsaas.model.Pagamento;
import java.sql.*;
import java.util.*;

public class PagamentoDAO implements GenericDAO<Pagamento> {
	@Override
	public void salvar(Pagamento o) throws SQLException {
		String sql = "INSERT INTO pagamento(assinatura_id,data_pagamento,data_vencimento,valor,status,forma_pagamento) VALUES(?,?,?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, false);
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(Pagamento o) throws SQLException {
		String sql = "UPDATE pagamento SET assinatura_id=?,data_pagamento=?,data_vencimento=?,valor=?,status=?,forma_pagamento=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, true);
			ps.executeUpdate();
		}
	}

	private void fill(PreparedStatement ps, Pagamento o, boolean id) throws SQLException {
		ps.setInt(1, o.getAssinaturaId());
		ps.setDate(2, DAOUtils.toSqlDate(o.getDataPagamento()));
		ps.setDate(3, DAOUtils.toSqlDate(o.getDataVencimento()));
		ps.setDouble(4, o.getValor());
		ps.setString(5, o.getStatus().name());
		ps.setString(6, o.getFormaPagamento());
		if (id)
			ps.setInt(7, o.getId());
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM pagamento WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public Pagamento buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM pagamento WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<Pagamento> listarTodos() throws SQLException {
		List<Pagamento> l = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM pagamento ORDER BY data_vencimento DESC");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				l.add(map(rs));
		}
		return l;
	}

	private Pagamento map(ResultSet rs) throws SQLException {
		return new Pagamento(rs.getInt("id"), rs.getInt("assinatura_id"),
				DAOUtils.toLocalDate(rs.getDate("data_pagamento")), DAOUtils.toLocalDate(rs.getDate("data_vencimento")),
				rs.getDouble("valor"), StatusPagamento.valueOf(rs.getString("status")),
				rs.getString("forma_pagamento"));
	}
}
