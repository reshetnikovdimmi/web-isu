package com.myisu_1.isu.models.Marwel;

public class ArtNaProdOst {
    String Article;
    String Name;
    String Sales;
    String Remains;

    public ArtNaProdOst() {
    }

    public ArtNaProdOst(String article, String name, String sales, String remains) {
        Article = article;
        Name = name;
        Sales = sales;
        Remains = remains;
    }

    public String getArticle() {
        return Article;
    }

    public void setArticle(String article) {
        Article = article;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSales() {
        return Sales;
    }

    public void setSales(String sales) {
        Sales = sales;
    }

    public String getRemains() {
        return Remains;
    }

    public void setRemains(String remains) {
        Remains = remains;
    }
}
