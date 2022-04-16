package io.lcalmsky.app.account.endpoint.controller.Validator;


import io.lcalmsky.app.account.endpoint.controller.SignUpForm;
import io.lcalmsky.app.account.infra.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {
    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors erros) {
        SignUpForm signUpForm = (SignUpForm) target;
        if (accountRepository.existsByEmail(signUpForm.getEmail())) {
            erros.rejectValue("email", "invalid.email", new Object[]{signUpForm.getEmail()},
                    "이미 사용중인 이메일입니다.");
        }

        if (accountRepository.existsByName(signUpForm.getName())) {
            erros.rejectValue("name", "invalid.name", new Object[]{signUpForm.getName()},
                    "이미 사용중인 닉네임입니다.");
        }

        if (!signUpForm.getPasswordCheck().equals(signUpForm.getPassword())) {
            erros.rejectValue("password", "disagree.password", new Object[]{signUpForm.getPasswordCheck()},
                    "패스워드가 일치하지 않습니다.");
        }
    }
}
