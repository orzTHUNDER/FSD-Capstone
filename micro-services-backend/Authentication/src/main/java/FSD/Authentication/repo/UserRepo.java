package FSD.Authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import FSD.Authentication.DAO.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findAllByEmail(String email);

}
