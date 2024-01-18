package raf.rs.projekat_test.repositories.user;

import raf.rs.projekat_test.entities.User;

import java.util.List;

public interface UserRepository {

    public User addUser(User user);
    public List<User> allUsers();
    public User findUser(Integer id);
    public User updateUser(Integer id, User user);
    public User findUserByEmail(String email);
    public void deleteUser(Integer id);

}
