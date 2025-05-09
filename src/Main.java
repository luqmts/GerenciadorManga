import Controllers.MangaController;
import Database.Conexao;
import Database.MangaDAO;
import Models.Manga;
import Models.MangaList;
import Models.Usuario;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int userInput = -1;

        Connection conn = Conexao.conectar();
        if (conn == null){
            System.out.println("Erro na conexão");
            return;
        }

        MangaDAO mdao = new MangaDAO(conn);
        Usuario usuario = new Usuario("Lucas", "teste@mail.com", "123456");

        MangaController mController = new MangaController(mdao, usuario);

        while (userInput != 5) {
            userPrompt();
            userInput = Integer.parseInt(sc.nextLine());

            switch (userInput) {
                case 1:
                    cadastrarManga(mController);
                    break;
                case 2:
                    atualizarManga(mController);
                    break;
                case 3:
                    obterMangas(mController);
                    break;
                case 4:
                    removerManga(mController);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Comando inválido.");
            }
        }
    }

    public static void userPrompt(){
        System.out.println("Digite o número da operação que deseja realizar:");
        System.out.println("1 - Adicionar novo mangá");
        System.out.println("2 - Editar mangá");
        System.out.println("3 - Visualizar mangás cadastrados");
        System.out.println("4 - Excluir mangá");
        System.out.println("5 - Sair");
    }

    public static void obterMangas(MangaController mController){
        System.out.println("Lista de mangás cadastrados:");
        MangaList mList = mController.obterMangas();

        for (Manga manga : mList.getListaManga())
            System.out.println(manga);
    }

    public static void cadastrarManga(MangaController mController){
        System.out.println("Digite o título do mangá: ");
        String title = sc.nextLine();

        System.out.println("Digite o nome do autor do mangá: ");
        String author = sc.nextLine();

        System.out.println("Insira o ano que o mangá foi lançado: ");
        String year = sc.nextLine();

        mController.cadastrarManga(title, author, Integer.parseInt(year));
    }

    public static void atualizarManga(MangaController mController){
        obterMangas(mController);
        System.out.println("Digite o id do mangá: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.println("Digite o título do mangá: ");
        String title = sc.nextLine();

        System.out.println("Digite o nome do autor do mangá: ");
        String author = sc.nextLine();

        System.out.println("Insira o ano que o mangá foi lançado: ");
        int year = Integer.parseInt(sc.nextLine());

        mController.atualizarManga(id, title, author, year);
    }

    public static void removerManga(MangaController mController){
        obterMangas(mController);

        System.out.println("Digite o id do mangá: ");
        int id = Integer.parseInt(sc.nextLine());

        mController.removerManga(id);
    }
}