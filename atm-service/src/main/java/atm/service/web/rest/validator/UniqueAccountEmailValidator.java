package atm.service.web.rest.validator;

import atm.client.errors.auth.account.AccountNotFoundException;
import atm.service.service.AccountService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueAccountEmailValidator implements ConstraintValidator<UniqueAccountEmail, String> {

    private final AccountService accountService;

    @Override
    public void initialize(UniqueAccountEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            accountService.findByEmail(value, false);
            return false;
        } catch (AccountNotFoundException ex) {
            return true;
        }
    }
}
