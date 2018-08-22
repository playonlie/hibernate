package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UserManager {
	
	protected SessionFactory sessionFactory;
	
	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {		
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
	
	protected void exit() {
		sessionFactory.close();		
	}
	
	protected void create() {
		User user = new User();
		user.setName("Hibernate");
		user.setPassword("hibernate");
		user.setFirstname("HI");
		user.setLastname("Ber");
		user.setEmail("Nate");
		user.setIsAdmin(false);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(user);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
	protected void read() {
		Session session = sessionFactory.openSession();
		long id = 4;
		User user = session.get(User.class, id);
		
		if (user != null) {
			System.out.println("From Sql"+ user.getName());
		}
		
		
		session.close();
	}
	
	protected void update() {
		User user = new User();
		user.setId(4);
		user.setName("NOname");
		user.setPassword("hibernate");
		user.setFirstname("HI");
		user.setLastname("Ber");
		user.setEmail("Nate");
		user.setIsAdmin(true);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.update(user);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
	protected void delete() {
		User user = new User();
		user.setId(4);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.delete(user);
		
		session.getTransaction().commit();
		session.close();
		
	}

	public static void main(String[] args) {
		UserManager manager= new UserManager();
		
		manager.setup();
		manager.delete();
		manager.exit();		
	}

}
