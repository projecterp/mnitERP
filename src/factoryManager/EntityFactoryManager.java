package factoryManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactoryManager {

	private static final EntityManagerFactory factory  = Persistence.createEntityManagerFactory("transactions-optional");

	public static EntityManagerFactory getFactoryManager(){
		return factory;
	}
}
//C:\Program Files (x86)\Google\Chrome\Application\chrome --user-data-dir=test --unsafely-treat-insecure-origin-as-secure=http://localhost:8888