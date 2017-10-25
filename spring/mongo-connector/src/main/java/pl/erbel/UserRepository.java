package pl.erbel;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {

    User findUserByFirstName(String firstName);

    User findUserByLastName(String lastName);

    User findUserByFirstNameOrLastName(String firstName, String lastName);

}

