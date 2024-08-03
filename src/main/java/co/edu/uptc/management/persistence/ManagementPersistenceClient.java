package co.edu.uptc.management.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import co.edu.uptc.management.clients.dto.ClientDTO;
import co.edu.uptc.management.constants.CommonConstants;

public class ManagementPersistenceClient extends FilePlain{
		private List<ClientDTO> listClientsDTO = new ArrayList<>();
		
		public void dumpFilePlain(String rutaArchivo) {
			List<String> records = new ArrayList<>();
			 for(ClientDTO clientDTO : listClientsDTO){
				 StringBuilder contentContact = new StringBuilder();
				 contentContact.append(clientDTO.getId()).append(CommonConstants.SEMI_COLON);
				 contentContact.append(clientDTO.getName()).append(CommonConstants.SEMI_COLON);
				 contentContact.append(clientDTO.getSubscriptionDate()).append(CommonConstants.SEMI_COLON);
				 contentContact.append(clientDTO.getPhoneNumber()).append(CommonConstants.SEMI_COLON);
				 contentContact.append(clientDTO.getEmail());
				 records.add(contentContact.toString());
			 }
			 this.writer(rutaArchivo, records);
		}
		
		public void loadFilePlain(String rutaNombreArchivo) {
			List<String> contentInLine = this.reader(rutaNombreArchivo);
			for(String row: contentInLine) {
				StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
				while(tokens.hasMoreElements()){
					String id = tokens.nextToken();
					String name = tokens.nextToken();
					String subscriptionDate = tokens.nextToken();
					String phoneNumber = tokens.nextToken();
					String email = tokens.nextToken();
					listClientsDTO.add(new ClientDTO(id, name, subscriptionDate, 
							phoneNumber, email));
				}
			}
		}

		public List<ClientDTO> getListClientsDTO() {
			return listClientsDTO;
		}

		public void setListClientsDTO(List<ClientDTO> listClientsDTO) {
			this.listClientsDTO = listClientsDTO;
		}
	}


