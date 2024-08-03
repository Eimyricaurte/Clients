package co.edu.uptc.management.clients.rest;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.uptc.management.clients.dto.ClientDTO;
import co.edu.uptc.management.persistence.ManagementPersistenceClient;


@Path("/ManagementClient")
public class ManagementClient {

	public static ManagementPersistenceClient managementPersistenceClient = new ManagementPersistenceClient();
	
	static {
		managementPersistenceClient.loadFilePlain("/data/clients.txt");
	}
	
	@GET
	@Path("/getClients")
	@Produces( { MediaType.APPLICATION_JSON } )
	public List<ClientDTO> getClients(){
		return managementPersistenceClient.getListClientsDTO();
	}
	
	@GET
	@Path("/getClientsById")
	@Produces( { MediaType.APPLICATION_JSON } )
	public ClientDTO getClientsById(@QueryParam("id") String id){
		for(ClientDTO clientDTO: managementPersistenceClient.getListClientsDTO()) {
			if(clientDTO.getId().equals(id)) {
				return clientDTO;
			}
		}
		return null;
	}
	
	
	@POST
	@Path("/createClient")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ClientDTO createClient(ClientDTO clientDTO) {
		if(managementPersistenceClient.getListClientsDTO().add(clientDTO)) {
			managementPersistenceClient.dumpFilePlain("clients.txt");
			return clientDTO;
		}
		return null;
	}
	
	@PUT
	@Path("/updateClient")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ClientDTO updateClient(ClientDTO clientDTO) {
		for(ClientDTO client: managementPersistenceClient.getListClientsDTO()) {
			if(client.getId().equals(clientDTO.getId())) {
				client.setId(clientDTO.getId());
				client.setName(clientDTO.getName());
				client.setSubscriptionDate(clientDTO.getSubscriptionDate());
				client.setPhoneNumber(clientDTO.getPhoneNumber());
				client.setEmail(clientDTO.getEmail());
				managementPersistenceClient.dumpFilePlain("clients.txt");
				return clientDTO;
			}
		}
		return null;
	}
	
	@PUT
	@Path("/updateClientAttribute")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ClientDTO updateClientAttribute(ClientDTO clientDTO) {
		for(ClientDTO client: managementPersistenceClient.getListClientsDTO()) {
			if(client.getId().equals(clientDTO.getId())) {
				if(!Objects.isNull(clientDTO.getName())) {
					client.setName(clientDTO.getName());
				}
				
				if(!Objects.isNull(clientDTO.getSubscriptionDate())) {
					client.setSubscriptionDate(clientDTO.getSubscriptionDate());
				}
				
				if(!Objects.isNull(clientDTO.getPhoneNumber())) {
					client.setPhoneNumber(clientDTO.getPhoneNumber());
				}
				
				if(!Objects.isNull(clientDTO.getEmail())) {
					client.setEmail(clientDTO.getEmail());
				}
				
				managementPersistenceClient.dumpFilePlain("clients.txt");
				return clientDTO;
			}
		}
		return null;
	}
	
	@DELETE
	@Path("/deleteClient")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ClientDTO deleteClient(@QueryParam("id") String id) {
		ClientDTO clientDTO = this.getClientsById(id);
		if(clientDTO != null) {
			managementPersistenceClient.getListClientsDTO().remove(clientDTO);
			managementPersistenceClient.dumpFilePlain("clients.txt");
		}
		return clientDTO;
	}
}
