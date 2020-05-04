package users.dao.factory;

import java.util.Properties;

public class RepositoryFactoryProvider {

	public static AbstractRepoFactory getRepositoryFactory(RepositoryType type, Properties properties) {
		switch(type) {
		case IN_MEMORY:
			return new InMemoryRepoFactory();
		case XML_FILE:
			return new XmlFileRepoFactory(properties);
		default: throw new RuntimeException("Unsupported repository type.");
		}
	}

}
