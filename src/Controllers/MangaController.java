package Controllers;

import Database.MangaDAO;
import Models.Manga;
import Models.MangaList;
import Models.Usuario;

public class MangaController {
    private final MangaDAO mangaDAO;
    private Usuario usuarioLogado;

    public MangaController(MangaDAO mdao, Usuario usuarioLogado) {
        this.mangaDAO = mdao;
        this.usuarioLogado = usuarioLogado;
    }

    public void cadastrarManga(String titulo, String autor, int ano) {
        Manga manga = new Manga(titulo, autor, ano);

        this.mangaDAO.inserir(manga);
        System.out.println("Mangá cadastrado com sucesso!");
    }

    public void atualizarManga(int id, String titulo, String autor, int ano) {
        Manga manga = obterMangas().getById(id);

        if (manga != null){
            manga.setTitulo(titulo);
            manga.setAutor(autor);
            manga.setAno(ano);

            mangaDAO.atualizar(manga);
            System.out.println("Mangá atualizado com sucesso!");
        } else
            System.out.println("Manga não encontrado!");
    }

    public MangaList obterMangas() {
        return mangaDAO.obter();
    }

    public void removerManga(int id) {
        Manga manga = obterMangas().getById(id);

        if (manga != null){
            mangaDAO.excluir(manga);
            System.out.println("Manga excluído!");
        } else
            System.out.println("Manga não encontrado!");
    }
}
