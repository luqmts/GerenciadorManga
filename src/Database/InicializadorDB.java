package Database;

import java.sql.Connection;
import java.sql.Statement;

public class InicializadorDB {
    public static void criarTabela() {
        String sqlDropTableMangas = "drop table if exists mangas";
        String sqlDropTableUsuarios = "drop table if exists usuarios";

        String sqlCreateTableMangas = """
            create table if not exists mangas (
                id integer primary key autoincrement,
                titulo text not null,
                autor text not null,
                ano integer not null
            );
        """;
        String sqlCreateTableUsuarios = """
            create table if not exists usuarios (
                id integer primary key autoincrement,
                nome text not null,
                email text not null,
                senha text not null
            )
        """;

        try (Connection conn = Conexao.conectar()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sqlDropTableMangas);
                stmt.execute(sqlDropTableUsuarios);
                stmt.execute(sqlCreateTableMangas);
                stmt.execute(sqlCreateTableUsuarios);

            }
        } catch (Exception e) {
            System.out.println("Erro ao criar Tabela: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        criarTabela();
    }
}
