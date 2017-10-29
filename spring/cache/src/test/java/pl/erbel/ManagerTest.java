package pl.erbel;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static javax.validation.Validation.buildDefaultValidatorFactory;

public class ManagerTest {

    @Test
    public void validationTest() {
        Manager invalidManager = new Manager("jan",
                "kowalski", 200, "hp");

        ValidatorFactory validatorFactory = buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Manager>> constraints = validator.validate(invalidManager);

        constraints.forEach(System.out::println);
    }


}