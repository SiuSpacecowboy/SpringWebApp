package springTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springTest.DataModels.*;
import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/rpg")
public class RPGController {

    private final CharacterDAO characterDAO;

    @Autowired
    public RPGController(CharacterDAO characterDAO) {
        this.characterDAO = characterDAO;
    }

    @GetMapping("/all_players")
    public String getAllPlayers(Model model) {
        model.addAttribute("allPlayers", characterDAO.getPlayers());
        return "rpg/all_players";
    }

    @GetMapping("{id}")
    public String getPlayer(@PathVariable("id") int id, Model model) {
        String res = "rpg/wrong_id";
        Player player = characterDAO.getIndexHero(id);
        if (player != null) {
            model.addAttribute("player", player);
            res = "rpg/player";
        }
        return res;
    }

    @GetMapping("newPlayer")
    public String newPlayer(@ModelAttribute("player") Player player) {
        return "rpg/new";
    }

   @PostMapping("/all_players")
   public String saveAHero(@ModelAttribute("player") @Valid Player player, BindingResult bindingResult) {
       if (bindingResult.hasErrors()) {
           return "rpg/new";
       }
       String finType = player.getClassType().toLowerCase();
       if (!finType.equals("archer") && !finType.equals("knight") && !finType.equals("mage")) {
           return "rpg/no_right_class";
       }
       characterDAO.savePlayer(player);
       return "redirect:/rpg/all_players";
   }

    @GetMapping("/{id}/edit")
    public String updateHero(@PathVariable("id") int id,
                             Model model) throws SQLException {
        String res = "rpg/wrong_id";
        Player player = characterDAO.getIndexHero(id);
        if (player != null) {
            model.addAttribute("player", player);
            res = "rpg/edit";
        }
        return res;
    }

  @PostMapping(value = "/{id}")
  //@PatchMapping("/{id}") // Если делать через ThymeLeaf, сам по себе не работает, т.к. в html указан пост.
  public String updateHero(@ModelAttribute("player") @Valid Player newPlayer,
                           BindingResult bindingResult,
                           @PathVariable("id") int id) {
      if (bindingResult.hasErrors()) {
          System.out.println("==================================================================");
          return "rpg/edit";
      }
      characterDAO.updatePlayer(newPlayer, id);
      return "redirect:/rpg/all_players";
  }

    @GetMapping("/{id}/delete")
    public String del(@PathVariable("id") int id) {
        return characterDAO.deletePlayer(id) ? "rpg/delete" : "rpg/wrong_id";
    }

    /* @PostMapping()
    public String create(@ModelAttribute("person") Person person) { // ДАННЫЙ класс показывает работу @ModelAttribute, который сам инициализирует параметр,
        personDAO.save(person);                                    // переданный внутрь метода, сам создает класс, использует сеттеры и добавляет атрибут в Model
        return "redirect:/people";                                 // С помощью model можно отправлять данные в наше представление(HTML) и выводить их пользователю.
    }*/                                        // Также, если создать аннотацию над всем методом, то он будет добавлен в каждую модель класса. Model - список!??
/*    @PostMapping("/all_heroes")
    public String saveAHero(@RequestParam("classType") String classType,
                            @RequestParam("nickname") String nickname) {
        String finType = classType.toLowerCase();
        switch (finType) {
            case "archer":
                Archer archer = new Archer(nickname);
                characterDAO.saveAHero(archer);
                break;
            case "knight":
                Knight knight = new Knight(nickname);
                characterDAO.saveAHero(knight);
                break;
            case "mage":
                Mage mage = new Mage(nickname);
                characterDAO.saveAHero(mage);
                break;
            default:
                return "rpg/no_right_class";
        }
        return "redirect:/rpg/all_heroes";
    }*/

  /*  @PostMapping(value = "/{id}")
    //@PatchMapping("/{id}") // Если делать через ThymeLeaf, сам по себе не работает, т.к. в html указан пост.
    public String updateHero(@RequestParam("setNickname") @Valid String nickname,
                         @PathVariable("id") int id) {
        characterDAO.updatePlayer(nickname, id);
        return "redirect:/rpg/all_players";
    }*/

   /* @PostMapping(value = "{/id}/delete") // Это не нужно, как post, а к update я не могу изменить конфигурацию.
    //@DeleteMapping("/{id}")
   public String deleteHero(@PathVariable("id") int id) {
        characterDAO.deleteHero(id);
        return "redirect:/rpg/all_heroes";
    }*/
}
