package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        try {
            userService.createUsersTable();
            userService.saveUser("Artem", "Iasko", (byte) 25);
            userService.saveUser("Vova", "Petrosyan", (byte) 23);
            userService.saveUser("Vage", "Gevorgyan", (byte) 27);
            userService.saveUser("Erik", "Osipov", (byte) 29);
            userService.getAllUsers().forEach(System.out::println);
            userService.cleanUsersTable();
            userService.dropUsersTable();

        } finally {
            Util.closeResources();
        }

    }
}
