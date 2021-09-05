package ru.itis;

public interface PasswordRepository {
    boolean checkPasswordInBlacklist(String password);
    void savePasswordInBlacklist(String password);
    void deletePasswordFromBlacklist(String password);
}
