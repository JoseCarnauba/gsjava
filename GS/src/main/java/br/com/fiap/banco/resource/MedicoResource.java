package br.com.fiap.banco.resource;

import java.sql.SQLException;
import java.util.List;

import org.apache.catalina.Service;

import br.com.fiap.banco.exception.BadInfoException;
import br.com.fiap.banco.exception.IdNotFoundException;
import br.com.fiap.banco.model.Medico;
import br.com.fiap.banco.service.MedicoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;


@Path("/medico") //http://localhost:8080/GSJava/api/medico
public class MedicoResource {

	private MedicoService service;
	
	public MedicoResource() throws ClassNotFoundException, SQLException {
		service = new MedicoService();
	}
	
	//Cadastrar
	
	//POST http://localhost:8080/07-WebApi/api/produto/ 
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Medico medico, @Context UriInfo uri) throws ClassNotFoundException, SQLException {
		try {
			service.cadastrar(medico);
		
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			
			uriBuilder.path(String.valueOf(medico.getIdmed()));

			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			
			//Retornar o status 400 bad request
			return Response.status(Status.BAD_REQUEST)
								.entity(e.getMessage()).build();
		}
	}
	
	//Listar
	
	//GET http://localhost:8080/GSJava/api/medico (Listar todos os medico cadastrados)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Medico> lista() throws ClassNotFoundException, SQLException {
		return service.listar();
	}
	
	//PUT http://localhost:8080/GSJava/api/medico/1 (Atualizar um produto)
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Medico medico, @PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
		try {
			medico.setidmed(idmed);
			service.atualizar(medico);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	//DELETE http://localhost:8080/GSJava/api/medico/1 (Apagar um medico do banco)
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
		try {
			service.remover(id);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
