package michal_liora;

public class Doctor extends Lecturer implements Comparable<Doctor> {
    // fix everything
    protected String[] articles;
    protected int articleCount;
    public Doctor(String name, String id, String degreeLevel, String degreeTitle, double salary, Department department) {
        super(name, id, degreeLevel, degreeTitle, salary, department);
        setArticles(new String[1]);
        setArticleCount(0);
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }

    public String[] getArticles() {
        return articles;
    }


    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }


    public int getArticleCount(){
        return this.articleCount;
    }

    @Override
    public String toString() {
        return "{" +
                "degreeTitle=" + degreeTitle +
                ", name=" + name +
                ", id=" + id +
                ", degreeLevel=" + degreeLevel +
                ", salary=" + salary +
                ", department=" + ((department != null) ? department.getName() : "(None)") +
                ", committees=" + committeesNamesToString() +
                ", articles=" + articlesToString() +
                "}";
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

    @Override
    public boolean equals(Object toCompare) {
        if (toCompare == null || getClass() != toCompare.getClass() || !super.equals(toCompare))
            return false;
        Doctor thatDoctor = (Doctor) toCompare;
        return articles.equals(thatDoctor.articles);
    }

    @Override
    public int compareTo(Doctor other) {
        return Integer.compare(getArticleCount(),other.getArticleCount());
    }

}
