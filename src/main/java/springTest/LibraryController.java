package springTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lib")
public class LibraryController {

    @GetMapping("/home")
    public String home() {
        return "lib/home";
    }

    @GetMapping("/books")
    public String books() {
        return "lib/books";
    }

    @GetMapping("/front")
    public String frontOf() {
        return "lib/front";
    }
}
