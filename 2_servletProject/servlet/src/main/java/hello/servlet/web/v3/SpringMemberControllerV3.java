package hello.servlet.web.v3;

import hello.servlet.basic.domain.Member;
import hello.servlet.basic.domain.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/kms/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping(value = "/new-form")
    public String newform(){
        return "new-form";
    }

    @GetMapping
    public String members(Model model){

        List<Member> members = memberRepository.findByAll();
        model.addAttribute("members",members);
        return "members";
    }

    @PostMapping(value = "/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("height") int height,
            @RequestParam("weight") int weight,
            Model model
    ){
        Member member = new Member(username,height,weight);
        memberRepository.save(member);

        model.addAttribute("member",member);
        return "save-result";
    }
}
