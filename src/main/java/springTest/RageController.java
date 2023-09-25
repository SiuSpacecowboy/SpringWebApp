package springTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RageController {

    @GetMapping("/rage")
    public String getRage() {
        return "rage";
    }
}
