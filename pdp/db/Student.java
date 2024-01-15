package uz.pdp.db;

import uz.pdp.entity.User;
import uz.pdp.util.Input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Student implements Repository<User> {
    private static final String Path = "users.txt";
    private static Student singleton;
    private final List<User> users;

    private Student(List<User> users) {
        this.users = users;
    }

    public static Student of() {
        if (singleton == null) {
            singleton = new Student(loadFromDB());
        }
        singleton.updateDB();
        return singleton;
    }

    @SuppressWarnings("unchecked")
    private static List<User> loadFromDB() {
        File file = new File(Path);
        if (file.exists()) {
            try (
                    InputStream inputStream = new FileInputStream(Path);
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
            ) {
                return (List<User>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }

    private void updateDB() {
        try (
                OutputStream outputStream = new FileOutputStream(Path);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            objectOutputStream.writeObject(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        users.add(user);
        System.out.println("User saved");
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void update(User user) {
        user.setName(Input.inputStr("Enter name"));
        System.out.println("User updated");
    }

    @Override
    public void delete(User user) {
        users.remove(user);
        System.out.println("User  removed");
    }
}
