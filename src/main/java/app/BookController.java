package app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

  @GetMapping("1")
  @ResponseBody
  public List<String> allBooks() {
    return List.of("Java", "Scala");
  }
}
