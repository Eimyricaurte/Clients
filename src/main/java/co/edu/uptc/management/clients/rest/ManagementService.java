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

import co.edu.uptc.management.clients.dto.ServiceDTO;
import co.edu.uptc.management.persistence.ManagementPersistenceService;

@Path("/ManagementService")
public class ManagementService {

public static ManagementPersistenceService managementPersistenceService = new ManagementPersistenceService();
	
	static {
		managementPersistenceService.loadFilePlain("/data/services.txt");
	}
	
	@GET
	@Path("/getServices")
	@Produces( { MediaType.APPLICATION_JSON } )
	public List<ServiceDTO> getServices(){
		return managementPersistenceService.getListServicesDTO();
	}
	
	@GET
	@Path("/getServicesById")
	@Produces( { MediaType.APPLICATION_JSON } )
	public ServiceDTO getServicesById(@QueryParam("idClient") String idClient){
		for(ServiceDTO serviceDTO: managementPersistenceService.getListServicesDTO()) {
			if(serviceDTO.getIdClient().equals(idClient)) {
				return serviceDTO;
			}
		}
		return null;
	}
	
	
	@POST
	@Path("/createService")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ServiceDTO createService(ServiceDTO serviceDTO) {
		if(managementPersistenceService.getListServicesDTO().add(serviceDTO)) {
			managementPersistenceService.dumpFilePlain("services.txt");
			return serviceDTO;
		}
		return null;
	}
	
	@PUT
	@Path("/updateService")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ServiceDTO updateService(ServiceDTO serviceDTO) {
		for(ServiceDTO service: managementPersistenceService.getListServicesDTO()) {
			if(service.getIdClient().equals(serviceDTO.getIdClient())) {
				service.setIdClient(serviceDTO.getIdClient());
				service.setCode(serviceDTO.getCode());
				service.setName(serviceDTO.getName());
				service.setPrice(serviceDTO.getPrice());
				managementPersistenceService.dumpFilePlain("services.txt");
				return serviceDTO;
			}
		}
		return null;
	}
	
	@PUT
	@Path("/updateServiceAttribute")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ServiceDTO updateServiceAttribute(ServiceDTO serviceDTO) {
		for(ServiceDTO service: managementPersistenceService.getListServicesDTO()) {
			if(service.getIdClient().equals(serviceDTO.getIdClient())) {
				if(!Objects.isNull(serviceDTO.getCode())) {
					service.setCode(serviceDTO.getCode());
				}
				
				if(!Objects.isNull(serviceDTO.getName())) {
					service.setName(serviceDTO.getName());
				}
				
				if(!Objects.isNull(serviceDTO.getPrice())) {
					service.setPrice(serviceDTO.getPrice());
				}
				
				managementPersistenceService.dumpFilePlain("services.txt");
				return serviceDTO;
			}
		}
		return null;
	}
	
	@DELETE
	@Path("/deleteService")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public ServiceDTO deleteService(@QueryParam("idClient") String idClient) {
		ServiceDTO serviceDTO = this.getServicesById(idClient);
		if(serviceDTO != null) {
			managementPersistenceService.getListServicesDTO().remove(serviceDTO);
			managementPersistenceService.dumpFilePlain("services.txt");
		}
		return serviceDTO;
	}
}
