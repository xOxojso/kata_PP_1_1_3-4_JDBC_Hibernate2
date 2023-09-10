package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Ivan", "Ivanov", (byte) 20);
        service.saveUser("Oleg", "Petrov", (byte) 35);
        service.saveUser("Mariya", "Zaharova", (byte) 25);
        service.saveUser("Vika", "Mikhalova", (byte) 31);
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
