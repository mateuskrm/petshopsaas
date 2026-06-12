package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.enums.TipoServico;
import br.com.petshopsaas.model.Servico;
import java.sql.*;
import java.util.*;

public class ServicoDAO implements GenericDAO<Servico> {
	@Override
	public void salvar(Servico o) throws SQLException {
		String sql = "INSERT INTO servico(nome,tipo_servico,valor_avulso,duracao_minutos) VALUES(?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, false);
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(Servico o) throws SQLException {
		String sql = "UPDATE servico SET nome=?,tipo_servico=?,valor_avulso=?,duracao_minutos=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			fill(ps, o, true);
			ps.executeUpdate();
		}
	}

	private void fill(PreparedStatement ps, Servico o, boolean id) throws SQLException {
		ps.setString(1, o.getNome());
		ps.setString(2, o.getTipoServico().name());
		ps.setDouble(3, o.getValorAvulso());
		ps.setInt(4, o.getDuracaoMinutos());
		if (id)
			ps.setInt(5, o.getId());
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM servico WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public Servico buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM servico WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<Servico> listarTodos() throws SQLException {
		List<Servico> l = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM servico ORDER BY nome");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				l.add(map(rs));
		}
		return l;
	}

	private Servico map(ResultSet rs) throws SQLException {
		return new Servico(rs.getInt("id"), rs.getString("nome"), TipoServico.valueOf(rs.getString("tipo_servico")),
				rs.getDouble("valor_avulso"), rs.getInt("duracao_minutos"));
	}
}
