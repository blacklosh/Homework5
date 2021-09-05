package ru.itis;

public class SignUpService {
    private PasswordRepository passwordRepository;

    public SignUpService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public boolean isBadPassword(String password){
        return passwordRepository.checkPasswordInBlacklist(password);
    }
}
