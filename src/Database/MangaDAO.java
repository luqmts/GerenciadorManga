package Database;

import Models.Manga;
import Models.MangaList;

import java.sql.*;

public class MangaDAO {
    private final Connection conn;

    public MangaDAO(Connection conn) { this.conn = conn; }

    public void inserir(Manga manga) {
        String sql = "INSERT INTO mangas (titulo, autor, ano) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, manga.getTitulo());
            stmt.setString(2, manga.getAutor());
            stmt.setInt(3, manga.getAno());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    manga.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir mangá: " + e.getMessage());
        }
    }

    public void atualizar(Manga manga) {
        String sql = "update mangas set titulo = ?, autor = ?, ano = ? where id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, manga.getTitulo());
            stmt.setString(2, manga.getAutor());
            stmt.setInt(3, manga.getAno());
            stmt.setInt(4, manga.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar mangá: " + e.getMessage());
        }
    }

    public void excluir(Manga manga) {
        String sql = "delete from mangas where id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, manga.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir mangá: " + e.getMessage());
        }
    }

    public MangaList obter() {
        String sql = "SELECT * FROM mangas ORDER BY id";
        MangaList mangaList = new MangaList();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String titulo = result.getString("titulo");
                String autor = result.getString("autor");
                int ano = result.getInt("ano");

                Manga manga = new Manga(id, titulo, autor, ano);
                mangaList.addManga(manga);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter mangás: " + e.getMessage());
        }

        return mangaList;
    }
}
