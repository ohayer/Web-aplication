package pl.coderslab;

public class klass {
    public static void main(String[] args) {
        UserDao dao = new UserDao();
        System.out.println(dao.findAll().toString());;

    }
}
