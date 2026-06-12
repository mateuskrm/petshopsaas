package br.com.petshopsaas.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
	void salvar(T objeto) throws SQLException;

	void atualizar(T objeto) throws SQLException;

	void deletar(int id) throws SQLException;

	T buscarPorId(int id) throws SQLException;

	List<T> listarTodos() throws SQLException;
}
