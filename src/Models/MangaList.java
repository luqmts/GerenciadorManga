package Models;

import java.util.ArrayList;

public class MangaList {
    ArrayList<Manga> listaManga;
    public MangaList(){
        this.listaManga = new ArrayList<>();
    }

    public ArrayList<Manga> getListaManga() {
        return listaManga;
    }

    public void addManga(Manga manga) {
        listaManga.add(manga);
    }

    public Manga getById(int id) {
        for  (Manga manga : listaManga) {
            if(manga.getId() == id) {
                return manga;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!listaManga.isEmpty()) {
            for(Manga manga : listaManga){
                sb.append(manga);
            }
        } else sb.append("Não há mangás cadastrados!");


        return sb.toString();
    }
}
