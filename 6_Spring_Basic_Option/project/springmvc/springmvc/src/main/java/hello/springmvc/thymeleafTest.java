package hello.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class thymeleafTest {

    @RequestMapping("/kms-response-v1")
    public ModelAndView view1(){
        ModelAndView modelAndView = new ModelAndView("/kms-thymeleaf")
                .addObject("data", "kms가 만든 페이지");
        return modelAndView;
    }

    @RequestMapping("/kms-response-v2")
    public String view2(Model model){
        model.addAttribute("data","kms가 model을 이용해서 넘긴 페이지");
        return "/kms-thymeleaf";
    }

    @RequestMapping("/kms-thymeleaf")
    public void view3(Model model){
        model.addAttribute("data","kms가 만든 페이지\n 이렇게 하면 void 타입으로 반환이 가능하다.");
    }
}
