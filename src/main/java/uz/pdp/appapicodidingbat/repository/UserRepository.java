package uz.pdp.appapicodidingbat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapicodidingbat.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByEmailAndIdNot(String email, Integer id);
    boolean existsByUserNameAndIdNot(String userName, Integer id);
}
