package driver;

public class Main {

    public static void main(String[] args) {
        Queries queries = new Queries();

        String id = "5faaa7c2700139301b4d8eb0";
        String cityId = "5faab487700139301b4d8eb8";

        queries.addCity(id, cityId);

        id = "5fc3caa76178882a231c9aa7";

        queries.deleteUserById(id);
    }
}
