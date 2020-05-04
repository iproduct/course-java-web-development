package users.dao.factory;

import java.util.Properties;

import users.dao.ResourceRepository;
import users.dao.UserRepository;
import users.dao.impl.JaxbResourceRepositoryImpl;
import users.dao.impl.JaxbUserRepositoryImpl;
import users.dao.impl.UserRepositoryImpl;

public class XmlFileRepoFactory implements AbstractRepoFactory {
	private Properties properties;

	public XmlFileRepoFactory(Properties properties) {
		this.properties = properties;
	}

	@Override
	public UserRepository getUserRepository() {
		return new JaxbUserRepositoryImpl(properties.getProperty("xml.file.users"));
	}

	@Override
	public ResourceRepository getResourceRepository() {
		return new JaxbResourceRepositoryImpl(properties.getProperty("xml.file.resources"));
	}

}
