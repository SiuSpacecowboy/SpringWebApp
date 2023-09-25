package springTest.DataModels;

public class Mage implements Heroes {

    private String nickname;
    private int id;

    public Mage(String nickname) {
        this.nickname = nickname;
    }
    public Mage(String nickname, int id) {
        this.nickname = nickname;
        this.id = id;
    }

    @Override
    public String classType() {
        return "Mage";
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
}

    public String getNickname() {
        return nickname;
    }
}
