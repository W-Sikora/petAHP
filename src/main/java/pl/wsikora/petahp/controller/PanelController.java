package pl.wsikora.petahp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class PanelController {

    @RequestMapping("")
    public String homeAction() {
        return "/panel/panel";
    }

    @RequestMapping("/pomoc")
    public String helpAction() {
        return "/panel/help/help";
    }
}
