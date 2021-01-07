package sample;

public class User {
    private String id;
    private String name;
    private String login;
    private String pass;
    private String post;
    private String numKart;
    private String data;
    private String time;

    public User(String id, String name, String login, String pass, String post, String numKart, String data, String time) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.pass = pass;
        this.post = post;
        this.numKart = numKart;
        this.data = data;
        this.time = time;
    }

    public User() { }

    public User(String id, String name, String login, String pass, String time, String data) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.pass = pass;
        this.data = data;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getNumKart() {
        return numKart;
    }

    public void setNumKart(String numKart) {
        this.numKart = numKart;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
