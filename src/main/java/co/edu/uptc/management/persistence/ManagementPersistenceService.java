package co.edu.uptc.management.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import co.edu.uptc.management.clients.dto.ServiceDTO;
import co.edu.uptc.management.constants.CommonConstants;

public class ManagementPersistenceService extends FilePlain{
			private List<ServiceDTO> listServicesDTO = new ArrayList<>();
			
			public void dumpFilePlain(String rutaArchivo) {
				List<String> records = new ArrayList<>();
				 for(ServiceDTO serviceDTO : listServicesDTO){
					 StringBuilder contentContact = new StringBuilder();
					 contentContact.append(serviceDTO.getCode()).append(CommonConstants.SEMI_COLON);
					 contentContact.append(serviceDTO.getIdClient()).append(CommonConstants.SEMI_COLON);
					 contentContact.append(serviceDTO.getName()).append(CommonConstants.SEMI_COLON);
					 contentContact.append(serviceDTO.getPrice());
					 records.add(contentContact.toString());
				 }
				 this.writer(rutaArchivo, records);
			}
			
			public void loadFilePlain(String rutaNombreArchivo) {
				List<String> contentInLine = this.reader(rutaNombreArchivo);
				for(String row: contentInLine) {
					StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
					while(tokens.hasMoreElements()){
						String code = tokens.nextToken();
						String idClient = tokens.nextToken();
						String name = tokens.nextToken();
						String price = tokens.nextToken();
						listServicesDTO.add(new ServiceDTO(code, idClient, name, price));
					}
				}
			}

			public List<ServiceDTO> getListServicesDTO() {
				return listServicesDTO;
			}

			public void setListServicesDTO(List<ServiceDTO> listServicesDTO) {
				this.listServicesDTO = listServicesDTO;
			}
		}




