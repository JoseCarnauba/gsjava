package br.com.fiap.banco.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.banco.dao.MedicoDao;
import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.factory.ConnectionFactory;
import br.com.fiap.banco.model.Medico;


public class MedicoService {
	
	private MedicoDao medicoDao;
	
	public MedicoService() throws ClassNotFoundException, SQLException {
			Connection conn = ConnectionFactory.getConnection();
			medicoDao = new MedicoDao(conn);
	}
	
	public void cadastrar(Medico medico) throws ClassNotFoundException, SQLException, BadInfoException {
		validar(medico);
		medicoDao.cadastrar(medico);
	}
		
	private void validar(Medico medico) throws BadInfoException {
		
	    // Nome obrigatório e não pode ter mais do que 30 caracteres
	    if (medico.getNome() == null || medico.getNome().length() > 30) {
	        throw new BadInfoException("Nome inválido, não pode ser nulo e no máximo 30 caracteres");
	    }

	    // Verifica se o número ID do hospital é válido
	    if (medico.getIdhosp() <= 0) {
	        throw new BadInfoException("Número ID do hospital inválido, deve ser maior que zero"); 
	        
	    }
	}
	    
	public void atualizar(Medico medico) throws ClassNotFoundException, SQLException, BadInfoException {
		validar(medico);
		medicoDao.atualizar(medico);
	}

	public void remover(int id) throws ClassNotFoundException, SQLException {
		medicoDao.remover(id);


	}

	public List<Medico> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
