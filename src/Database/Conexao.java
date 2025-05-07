package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar(){
        String url = "jdbc:sqlite:d:/bullshit/db/mangas.db";

        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Conectado com sucesso!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }

        return null;
    }
}
