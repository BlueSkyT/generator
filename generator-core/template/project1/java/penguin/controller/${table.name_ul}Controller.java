package penguin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import penguin.model.${table.name_ul}Model;
import penguin.service.${table.name_ul}Service;

@Controller
public class ${table.name_ul}Controller {

    ${table.name_ul}Service ${table.name_ulfl};

    @RequestMapping("index")
    public void index(${table.name_ul}Model ${table.name_ulfl}) {

    }
}