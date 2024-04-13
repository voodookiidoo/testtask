package ru.task;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import ru.task.transport.dto.UpdateRequestDTO;

@SpringBootTest
public class ValidationTest {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Test
    public void testValidation() {
        UpdateRequestDTO bean = new UpdateRequestDTO();
        bean.setBrand("1").setYear(10);
        Errors errors = new BeanPropertyBindingResult(bean, "myBean");
        validator.validate(bean, errors);
        Assertions.assertThat(errors.hasErrors()).isTrue();
    }
}
