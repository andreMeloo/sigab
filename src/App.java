import model.dao.BaseDAO;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        BaseDAO dao = new BaseDAO();
        System.out.println(dao.getConnection());
    }
}
