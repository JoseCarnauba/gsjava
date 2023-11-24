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
	
	
	public List<Associado> listarAssociado(String nome) throws Exception{
		
		PreparedStatement stm = conn.prepareStatement("select * from t_associado where med_nome like ?");
		
		//Setar o parametro no comando SQL
		stm.setString(1, "%"+nome+"%");
		
		//Executar o comando SQL
		ResultSet result = stm.executeQuery();
		
		//Criar a lista de produtos
		List<Associado> lista = new ArrayList<>();
		
		//Recuperar os produtos encontrados e adicionar na lista
		while (result.next()) {
			Associado associadoo = parse(result);
			lista.add(associado);
		}
		//Retornar a lista
		return lista;
	}
	
	public void cadastrar(Associado associado) throws ClassNotFoundException, SQLException {

		// Criar o objeto com o comando SQL configuravel
		PreparedStatement stm = conn.prepareStatement("INSERT INTO" + " T_ASSOCIADO (id_medico, med_nome, med_crm,"
				+ "med_especialidade) "
				+ "values (?, ?, ?, ?)");

		// Setar os valores no comando SQL
		stm.setInt(1, medico.getId());
		stm.setString(2, medico.getNome());
		stm.setInt(3, medico.getCrm());
		stm.setString(4, medico.getEspecialidade());

		// Executar o comando SQL
		stm.executeUpdate();
	}
	
	public List<MAssociado> listar() throws ClassNotFoundException, SQLException {

		// Criar o comando SQL
		PreparedStatement stm = conn.prepareStatement("select * from t_medico");

		// Executar o comando SQL
		ResultSet result = stm.executeQuery();

		// Criar a lista de produtos
		List<Medico> lista = new ArrayList<Medico>();

		// Percorrer todos os registros encontrados
		while (result.next()) {
			Medico prod = parse(result);
			// Adicionar na lista
			lista.add(prod);
		}
		// Retornar a lista de produto
		return lista;
	}
	
	public Medico pesquisar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		// PreparedStatement (com select)
		PreparedStatement stm = conn.prepareStatement("select * from" + " t_medico where id_medico = ?");

		// Setar o id no comando sql (select)
		stm.setInt(1, id);

		// Executar o comando SQL
		ResultSet result = stm.executeQuery();

		// Verifica se encontrou o produto
		if (!result.next()) {
			// Lança uma exception pois o produto não foi encontrado
			throw new IdNotFoundException("Medico não cadastrado");
		}
		Medico medico = parse(result);
		// Retornar o produto
		return medico;
	}
	
	//Método auxiliar que recebe o resultado do banco e retorna o objeto produto
		private Medico parse(ResultSet result) throws SQLException {
			
			// Obter valores da tabela médico
			
			int codigo = result.getInt("id_medico");
			String nome = result.getString("med_nome");
			int crm = result.getInt("med_crm");
			String especialidade = result.getString("med_especialidade");
			
			// Instanciar com os valores
			Medico medico = new Medico(codigo, nome, crm, especialidade);
			
			return medico;
		}
		
		public void atualizar(Medico medico) throws ClassNotFoundException, SQLException, IdNotFoundException {

			// PreparedStatement
			PreparedStatement stm = conn.prepareStatement("update t_medico set med_nome = ?, med_crm = ?,"
					+ " med_especialidade = ? where id_medico = ?");
			// Setar os parametros na Query
			stm.setString(1, medico.getNome());
			stm.setInt(2, medico.getCrm());
			stm.setString(3, medico.getEspecialidade());
			stm.setInt(4, medico.getId());
			
			// Executar a Query
			int linha = stm.executeUpdate();
			if (linha == 0)
				throw new IdNotFoundException("Medico não cadastrado, impossivel atualizar");
		}
		
		public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
			
			// PreparedStatement
			PreparedStatement stm = conn.prepareStatement("delete from t_medico where id_medico = ?");
			
			// Setar os parametros na Query
			stm.setInt(1, id);
			
			// Executar a Query
			int linha = stm.executeUpdate();
			if (linha == 0)
				throw new IdNotFoundException("Medico não cadastrado para remoção");
		}
		
}

}
