package Database;

import Models.Usuario;

import java.sql.*;

public class UsuarioDAO {
    private final Connection conn;

    public UsuarioDAO(Connection conn) {this.conn = conn;}

    public void inserir(Usuario usuario) {
        String sql = "insert into usuarios (nome, email, senha) values (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário" + e.getMessage());
        }
    }

    public void atualizar(Usuario usuario) {
        String sql = "update usuarios set nome = ?, email = ?, senha = ? where id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário" + e.getMessage());
        }
    }

    public void excluir(Usuario usuario) {
        String sql = "delete from usuarios where id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, usuario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário" + e.getMessage());
        }
    }

    public void obter() {
        String sql = "select * from usuarios order by id";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro ao obter usuários" + e.getMessage());
        }
    }
}
