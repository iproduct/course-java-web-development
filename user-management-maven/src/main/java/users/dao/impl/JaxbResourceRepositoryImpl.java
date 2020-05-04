package users.dao.impl;

import users.dao.ResourceRepository;
import users.model.Resource;
import users.model.User;

// Problem 4.II
public class JaxbResourceRepositoryImpl extends JAXBEntityRepository<Resource> implements ResourceRepository {

	public JaxbResourceRepositoryImpl(String xmlFileName) {
		super(xmlFileName);
	}

}
