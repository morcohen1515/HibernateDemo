package il.ac.hit.hibernatedemo;

import java.util.List;

/**
 * every module for this program needs to implement these basic methods.
 */

public interface IModel {
	public User login(String userName, String password) throws ProductManagmentException;
    public void signUp(User u) throws ProductManagmentException;
    public List<User> chackIfUserExists() throws ProductManagmentException;
}
