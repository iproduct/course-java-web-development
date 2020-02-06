package notification.rest;

import java.io.File;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import notification.jaxb.Note;
import notification.jaxb.Notes;

@Path("notifications")
public class NotificationsResource {
	public static String NOTIFICATION_XML_DB_PATH = "D:/Course_Java_Web_Development/workspace_java_2019/NotesXMLDB/notesNoNamespace.xml";
	public static String NOTIFICATION_XML_SCHEMA_PATH = "D:/Course_Java_Web_Development/workspace_java_2019/NotesXMLDB/notesNoNamespace.xsd";

	@GET
	@Produces({ "application/xml", "application/json" })
	public Notes getAllNotes() {
		Notes result = new Notes();
		try {
			JAXBContext jc = JAXBContext.newInstance("notification.jaxb");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schemaXSD = schemaFactory.newSchema(new File(
					NOTIFICATION_XML_SCHEMA_PATH));
			unmarshaller.setSchema(schemaXSD);
			result = (Notes) unmarshaller.unmarshal(new File(
					NOTIFICATION_XML_DB_PATH));
			// for (Note n : result.getNote()) {
			// System.out.println(n);
			// }

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return result;
	}

	@POST
	@Consumes({ "application/xml", "application/json" })
	public void addNote(Note note) {
		// if (note == null) {
		// throw new NotificationException("Note argument is null");
		// }
		System.out.println("--------------> " + note.getFrom());
		try {
			JAXBContext jc = JAXBContext.newInstance("notification.jaxb");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			File db = new File(NOTIFICATION_XML_DB_PATH);
			Notes notes = (Notes) unmarshaller.unmarshal(db);
			List<Note> list = notes.getNote();
			long maxId = 0;
			for (Note n : list) {
				if (n.getId() > maxId) {
					maxId = n.getId();
				}
			}
			note.setId(maxId + 1);
			list.add(note);

			// Marshall notes to xml
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(notes, db);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteNote(@PathParam("id") String idStr) {
		Response res = Response.noContent().build();
		try {
			long id=-1;
			try {
				id = Long.parseLong(idStr);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
			JAXBContext jc = JAXBContext.newInstance("notification.jaxb");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			File db = new File(NOTIFICATION_XML_DB_PATH);
			Notes notes = (Notes) unmarshaller.unmarshal(db);
			List<Note> list = notes.getNote();
			boolean removed = false;
			for (Note n : list) {
				if (n.getId() == id) {
					list.remove(n);
					removed = true;
					break;
				}
			}
			System.out.println("------>removed = " + removed);
			if (!removed) {
//				throw new RuntimeException("Object with ID=" + id
//						+ "does not exist");
				res = Response.status(404)
						.entity("Object with ID=" + id + " does not exist")
						.build();
			}

			// Marshall notes to xml
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(notes, db);
		} catch (JAXBException e) {
			res = Response.status(404).build();
		}
		return res;

	}

}
