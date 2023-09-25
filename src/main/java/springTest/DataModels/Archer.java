package springTest.DataModels;

public class Archer implements Heroes {

    private String nickname;
    private int id;

    public Archer(String nickname) {
        this.nickname = nickname;
    }

    public Archer(String nickname,int id) {
        this.nickname = nickname;
        this.id = id;
    }

    @Override
    public String classType() {
        return "Archer";
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
        this.id =id;
    }

    public String getNickname() {
        return nickname;
    }
}
