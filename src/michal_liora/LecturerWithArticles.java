package michal_liora;

abstract class LecturerWithArticles extends Lecturer implements Comparable<LecturerWithArticles> {
    private String[] articles;
    private int articleCount;

    public LecturerWithArticles(String name, String id, String degreeLevel, String degreeTitle, double salary, Department department) {
        super(name, id, degreeLevel, degreeTitle, salary, department);
        setArticles(new String[1]);
        setArticleCount(0);
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public String[] getArticles() {
        return articles;
    }

    public int getArticleCount(){
        return this.articleCount;
    }

    @Override
    public int compareTo(LecturerWithArticles other) {
        return Integer.compare(getArticleCount(),other.getArticleCount());
    }
    public String articlesToString(){

        String articlesStr = "[";
        int i = 0;
        for (; i < (committeesCount - 1); i++){
            articlesStr += articles[i] + ", " ;
        }
        if(committeesCount != 0) {
            articlesStr += articles[i];
        }
        articlesStr += "]";
        return articlesStr;
    }
}
