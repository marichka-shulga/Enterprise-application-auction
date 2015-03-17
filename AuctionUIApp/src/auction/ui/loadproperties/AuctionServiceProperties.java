package auction.ui.loadproperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import auction.ui.log.LogFactory;

public class AuctionServiceProperties {

	private static final Logger LOGGRER = LogFactory.getLogger(AuctionServiceProperties.class);	
	
	private String namespaceService;
	private String serviceName;
	private String wsdlURL;		
	
	public AuctionServiceProperties(){
    	Properties properties = new Properties();
    	InputStream fileInput = null;
		try {
			fileInput = getClass().getResourceAsStream("/configuration.properties");
			properties.load(fileInput);
			
			namespaceService = properties.getProperty("namespace_service");
			serviceName = properties.getProperty("service_name");
			wsdlURL = properties.getProperty("wsdl_url");
			
		} catch (Exception e) {
			LOGGRER.error("Is not satisfied AuctionServiceProperties={}, reason={}", e, e.getMessage());
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					LOGGRER.error("Is not satisfied AuctionServiceProperties={}, reason={}", e, e.getMessage());
				}
			}
		}
	}
	
	public String getNamespaceService() {
		return namespaceService;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getWsdlURL() {
		return wsdlURL;
	}

}
