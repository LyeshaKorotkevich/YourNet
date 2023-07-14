package by.gsu.yournet.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
