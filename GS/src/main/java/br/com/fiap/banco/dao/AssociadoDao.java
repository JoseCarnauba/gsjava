package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Associado;

public class AssociadoDao {
	
private Connection conn;
	
	public AssociadoDao(Connection conn) { this.conn = conn;}
	
	public List<Associado> pesquisarPorNome(String nome) throws SQLException{
		
		PreparedStatement stm = conn.prepareStatement("select * from t_associado where nm_associado like ?");
		
		stm.setString(1, "%"+nome+"%");
		
		ResultSet result = stm.executeQuery();
		
	
		List<Associado> lista = new ArrayList<>();
		
		while (result.next()) {
			Associado associado = parse(result);
			lista.add(associado);
		}
	
		return lista;
	}
	
	public void cadastrar(Associado associado) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("INSERT INTO" + " t_associado (id_associado, nm_paciente, ds_email,"
				+ "nm_telefone, nr_convenio, nr_convenio, ds_genero, ds_faixa_etaria) "
				+ "values (?, ?, ?, ?, ?)");

		// Setar os valores no comando SQL
		stm.setInt(1, associado.getId());
		stm.setString(2, associado.getNome());
		stm.setInt(4, associado.getTelefone());
		stm.setInt(5, associado.getConvenio());
	

		stm.executeUpdate();
	}
	
	public List<Associado> listar() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("select * from t_associado");

		ResultSet result = stm.executeQuery();

		List<Associado> lista = new ArrayList<Associado>();

		while (result.next()) {
			Associado pac = parse(result);
			
			lista.add(pac);
		}
		
		return lista;
	}
	
	public Associado pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		PreparedStatement stm = conn.prepareStatement("select * from" + " t_ where id_paciente = ?");

		stm.setInt(1, id);

		ResultSet result = stm.executeQuery();

		if (!result.next()) {
			
			throw new IdNotFoundException("Paciente não cadastrado.");
		}
		Associado associado = parse(result);
	
		return associado;
		
		}
	
		private Associado parse(ResultSet result) throws SQLException {
			
			
			
			int codigo = result.getInt("id_associado");
			String nome = result.getString("nm_associado");
			int telefone = result.getInt("nm_telefone");
			int convenio = result.getInt("nr_convenio");
			

			Associado associado = new Associado(codigo, nome, telefone, convenio);
			
			return associado;
		}
		
		public void atualizar(Associado associado) throws ClassNotFoundException, SQLException, IdNotFoundException {

			
			PreparedStatement stm = conn.prepareStatement("update t_associado set nm_associado = ?,"
					+ " nm_telefone = ?, nr_convenio = ?, where id_paciente = ?");
			
			
			stm.setString(1, associado.getNome());
			stm.setInt(2, associado.getTelefone());
			stm.setInt(3, associado.getConvenio());
			stm.setInt(4, associado.getId());
			
			
			int linha = stm.executeUpdate();
			if (linha == 0)
				throw new IdNotFoundException("Associado não cadastrado, impossivel atualizar");
		}
		
		public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
			
			// PreparedStatement
			PreparedStatement stm = conn.prepareStatement("delete from t_associado where id_associado = ?");
			
			
			stm.setInt(1, id);
			
			
			int linha = stm.executeUpdate();
			if (linha == 0)
				throw new IdNotFoundException("Associado não cadastrado para remoção");
		}

}
