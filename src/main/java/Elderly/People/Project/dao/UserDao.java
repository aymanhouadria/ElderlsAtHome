package Elderly.People.Project.dao;

import java.util.Collection;
import Elderly.People.Project.model.UserDetails;

public interface UserDao {
    UserDetails loadUserByUsername(String username, String password);
    Collection<UserDetails> listAllUsers();
}
