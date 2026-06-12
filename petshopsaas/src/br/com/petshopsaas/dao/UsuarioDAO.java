package br.com.petshopsaas.dao;

import br.com.petshopsaas.db.DatabaseConnection;
import br.com.petshopsaas.enums.TipoUsuario;
import br.com.petshopsaas.model.Usuario;
import java.sql.*;
import java.util.*;

public class UsuarioDAO implements GenericDAO<Usuario> {
	@Override
	public void salvar(Usuario u) throws SQLException {
		String sql = "INSERT INTO usuario(nome,email,senha,telefone,tipo_usuario) VALUES(?,?,?,?,?)";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, u.getNome());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getSenha());
			ps.setString(4, u.getTelefone());
			ps.setString(5, u.getTipoUsuario().name());
			ps.executeUpdate();
		}
	}

	@Override
	public void atualizar(Usuario u) throws SQLException {
		String sql = "UPDATE usuario SET nome=?,email=?,senha=?,telefone=?,tipo_usuario=? WHERE id=?";
		try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, u.getNome());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getSenha());
			ps.setString(4, u.getTelefone());
			ps.setString(5, u.getTipoUsuario().name());
			ps.setInt(6, u.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void deletar(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM usuario WHERE id=?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public Usuario buscarPorId(int id) throws SQLException {
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM usuario WHERE id=?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	@Override
	public List<Usuario> listarTodos() throws SQLException {
		List<Usuario> l = new ArrayList<>();
		try (Connection c = DatabaseConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM usuario ORDER BY nome");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				l.add(map(rs));
		}
		return l;
	}

	private Usuario map(ResultSet rs) throws SQLException {
		return new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"),
				rs.getString("telefone"), TipoUsuario.valueOf(rs.getString("tipo_usuario")));
	}
}
