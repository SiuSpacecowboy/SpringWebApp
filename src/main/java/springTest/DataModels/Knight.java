package springTest.DataModels;

public class Knight implements Heroes {

    private String nickname;
    private int id;

    public Knight(String nickname) {
        this.nickname = nickname;
    }

    public Knight(String nickname, int id) {
        this.nickname = nickname;
        this.id = id;
    }

    @Override
    public String classType() {
        return "Knight";
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