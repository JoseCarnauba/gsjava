package br.com.fiap.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.model.Medico;

	public class MedicoDao {
	
		private Connection conn;
	
		public MedicoDao(Connection conn) {
			this.conn = conn;
	}
		
		public List<Medico> listar() throws ClassNotFoundException, SQLException {
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM T_MEDICO");
			ResultSet result = stm.executeQuery();
			List<Medico> lista = new ArrayList<Medico>();
			
			while (result.next()) {
				Medico med = parse(result);
				lista.add(med);
			}
			
			return lista;		
		}
		
	public void cadastrar(Medico medico) throws ClassNotFoundException, SQLException {
		
		PreparedStatement stm = conn.prepareStatement("INSERT INTO" + " T_MEDICO(id_medico, id_hospital, nm_medico,"
				+ " nr_crm, nm_especialidade)"
				+ "values (?, ?, ?, ?, ?)");
				
	 	stm.setInt(1, medico.getIdmed());
		stm.setInt(2, medico.getIdhosp());
		stm.setString(3, medico.getNome());
		stm.setInt(4, medico.getCrm());
		stm.setString(5, medico.getEspecialidade());
		
		stm.executeUpdate();
		
	}
	
	public Medico pesquisar(int id) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conn.prepareStatement("select * from" + " t_medico where id_medico = ?");
		
		stm.setInt(1, id);
		ResultSet result = stm.executeQuery();

		if (!result.next()) {
		}
		Medico medico = parse(result);
		return medico;
	}
	
	public void atualizar(Medico medico) throws ClassNotFoundException, SQLException {

		// PreparedStatement
		PreparedStatement stm = conn.prepareStatement("update t_medico set id_hospital, nm_medico = ?, nr_crm = ?,"
				+ " nm_especialidade= ?, where id_medico = ?");
		
		stm.setInt(1, medico.getIdhosp());
		stm.setString(2, medico.getNome());
		stm.setInt(3, medico.getCrm());
		stm.setString(4, medico.getEspecialidade());
		stm.setInt(5, medico.getIdmed());
	
		int linha = stm.executeUpdate();
		if (linha == 0);
	
	}
	
	public void remover(int id) throws ClassNotFoundException, SQLException {
		
	PreparedStatement stm = conn.prepareStatement("delete from t_medico where id_medico = ?");

	stm.setInt(1, id);
	
	int linha = stm.executeUpdate();
	if (linha == 0);
	
	}
	
	private Medico parse(ResultSet result) {

		return null;
	}
	
}	