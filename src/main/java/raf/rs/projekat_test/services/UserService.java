package raf.rs.projekat_test.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.digest.DigestUtils;
import raf.rs.projekat_test.entities.User;
import raf.rs.projekat_test.entities.enums.UserStatus;
import raf.rs.projekat_test.repositories.user.UserRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public User addUser(User user) {
        return this.userRepository.addUser(user);
    }

    public List<User> allUsers() {
        return this.userRepository.allUsers();
    }

    public User findUser(Integer id) {
        return this.userRepository.findUser(id);
    }

    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }
    public User updateUser(Integer id, User user) {
        return this.userRepository.updateUser(id, user);
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteUser(id);
    }

    public String login(String email, String password) {
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUserByEmail(email);
        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();

        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withSubject(email)
                .withClaim("type", user.getType().toString())
                .withClaim("status", user.getStatus().toString())
                .sign(algorithm);

    }

    public boolean isAuthorized(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
        String status = jwt.getClaim("status").asString();

        User user = this.userRepository.findUserByEmail(email);

        if (user == null || status.equals(UserStatus.INACTIVE.toString())) {
            return false;
        }

        return true;

    }


}
