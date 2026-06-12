package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.enums.StatusAgendamento;
import br.com.petshopsaas.model.Agendamento;
import java.sql.*;
import java.util.*;

public class AgendamentoDAO implements GenericDAO<Agendamento> {
	@Override
	public void salvar(Agendamento o) throws SQLException {
		String sql = "INSERT INTO agendamento(pet_id,funcionario_id,servico_id,assinatura_id,data_hora,status,observacoes) VALUES(?,?,?,?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, false);
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(Agendamento o) throws SQLException {
		String sql = "UPDATE agendamento SET pet_id=?,funcionario_id=?,servico_id=?,assinatura_id=?,data_hora=?,status=?,observacoes=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, true);
			ps.executeUpdate();
		}
	}

	private void fill(PreparedStatement ps, Agendamento o, boolean id) throws SQLException {
		ps.setInt(1, o.getPetId());
		ps.setInt(2, o.getFuncionarioId());
		ps.setInt(3, o.getServicoId());
		if (o.getAssinaturaId() == null)
			ps.setNull(4, Types.INTEGER);
		else
			ps.setInt(4, o.getAssinaturaId());
		ps.setTimestamp(5, DAOUtils.toTimestamp(o.getDataHora()));
		ps.setString(6, o.getStatus().name());
		ps.setString(7, o.getObservacoes());
		if (id)
			ps.setInt(8, o.getId());
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM agendamento WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public Agendamento buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM agendamento WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<Agendamento> listarTodos() throws SQLException {
		List<Agendamento> l = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM agendamento ORDER BY data_hora DESC");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				l.add(map(rs));
		}
		return l;
	}

	private Agendamento map(ResultSet rs) throws SQLException {
		int assinatura = rs.getInt("assinatura_id");
		Integer assId = rs.wasNull() ? null : assinatura;
		return new Agendamento(rs.getInt("id"), rs.getInt("pet_id"), rs.getInt("funcionario_id"),
				rs.getInt("servico_id"), assId, DAOUtils.toLocalDateTime(rs.getTimestamp("data_hora")),
				StatusAgendamento.valueOf(rs.getString("status")), rs.getString("observacoes"));
	}
}
