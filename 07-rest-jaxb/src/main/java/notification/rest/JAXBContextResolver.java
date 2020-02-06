package notification.rest;

import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;

@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext> {

	private JAXBContext context;
	private Class<?>[] types = 
		{ notification.jaxb.Notes.class, 
			notification.jaxb.Note.class};

	public JAXBContextResolver() throws Exception {
          //this.context =  new JSONJAXBContext(JSONConfiguration.natural().build(), types); 
     }

	public JAXBContext getContext(Class<?> objectType) {
		for (Class<?> type : types) {
			if(type.equals(objectType)) {
				return context;
			}
		}
		return null;
	}
}

//@Provider
//@Produces(MediaType.APPLICATION_JSON)
//public class JacksonConfig implements ContextResolver<ObjectMapper> {
//    private ObjectMapper objectMapper;  
//
//    public JacksonConfig() throws Exception  {  
//
//        objectMapper = new ObjectMapper();
//        objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
//        objectMapper.setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));  
//        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
//    }
//
//    @Override
//    public ObjectMapper getContext(Class<?> arg0) {
//        return objectMapper;
//    }  
//}