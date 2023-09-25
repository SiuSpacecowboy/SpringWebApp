package springTest;

import org.springframework.stereotype.Component;
import springTest.DataModels.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterDAO {

    private static int ID_COUNT;
    private static final String URL = "jdbc:mysql://localhost:3306/rpg";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/*    {
        players = new ArrayList<>();
        players.add( new Player(++ID_COUNT, "Archer", "Sadovyj-Gnomik", 15, "sad@mail.ru"));
        players.add( new Player(++ID_COUNT, "Knight", "Tolstyj", 29, "tol@mail.com"));
        players.add( new Player(++ID_COUNT, "Mage", "Petux", 54, "val@mail.com"));
    }*/

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        String SQL = "SELECT * FROM players";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL);
            while (result.next()) {
                Player player = new Player();
                player.setId(result.getInt("id"));
                player.setClassType(result.getString("classType"));
                player.setNickname(result.getString("nickname"));
                player.setAge(result.getInt("age"));
                player.setEmail(result.getString("email"));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public Player getIndexHero(int id) {
        Player player = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
             player = new Player(resultSet.getInt("id"), resultSet.getString("classType"),
                    resultSet.getString("nickname"), resultSet.getInt("age"), resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return player;
    }

    public void savePlayer(Player player) {
        try {
            PreparedStatement prep = connection.prepareStatement("INSERT INTO players VALUES(2, ?, ?, ?, ?)");
            prep.setString(1, player.getClassType());
            prep.setString(2, player.getNickname());
            prep.setInt(3, player.getAge());
            prep.setString(4, player.getEmail());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updatePlayer(Player gamer, int id) {
        Player player = getIndexHero(id);
        if (player == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE players SET classType = ?, nickname = ?, age = ?, email = ? WHERE id = ?");
            preparedStatement.setString(1, gamer.getClassType());
            preparedStatement.setString(2, gamer.getNickname());
            preparedStatement.setInt(3, gamer.getAge());
            preparedStatement.setString(4, gamer.getEmail());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deletePlayer(int id) {
        Player player = getIndexHero(id);
        if (player == null) {
         return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM players WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
/*       if (player != null) {
            if (player.getId() != ID_COUNT) {
                for (int i = player.getId() - 1; i < players.size(); i++) {
                    players.get(i).setId(i);
                }
            }
            ID_COUNT--;
            players.remove(id - 1);
            res = true;
        } */
        return true;
    }

}
