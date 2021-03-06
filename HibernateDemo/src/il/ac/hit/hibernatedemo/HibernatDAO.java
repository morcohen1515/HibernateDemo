package il.ac.hit.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;

public class HibernatDAO implements IModel{

    SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
    
	@Override
	public User login(String userName, String password) throws ProductManagmentException {
		 Session sess = factory.openSession();
		 try{
			sess.beginTransaction();
			
			List<User> users = sess.createQuery("from User").list();
			int flag = 0;
			for(int i=0; i<users.size(); i++) {
				if(users.get(i).getName().equals(userName) && users.get(i).getPassword().equals(password)) {
					flag = i;
				}	
			}
			
			sess.beginTransaction().commit();
		 	return users.get(flag);
		 }
		 finally {
		 	sess.close();
		 }	
	}

    @Override
    public void signUp(User u) throws ProductManagmentException {
        Session sess = factory.openSession();
        try{
            sess.beginTransaction();
			List<User> users = sess.createQuery("from User").list();
			for(int i=0; i<users.size(); i++) {
				if(users.get(i).getName().equals(u.getName())) {
					throw new ProductManagmentException("This username already exists");
				}
			}
            sess.save(u);
            sess.getTransaction().commit();
        }
        finally {
            sess.close();
        }
    }

	@Override
	public List<User> chackIfUserExists() throws ProductManagmentException {
        Session sess = factory.openSession();
        try{
            sess.beginTransaction();
			List<User> users = sess.createQuery("from User").list();
            sess.getTransaction().commit();
            return users;
        }
        finally {
            sess.close();
        }
	}
}