package kms.itemservice.domain.champion.kms;

import kms.itemservice.domain.champion.Champion;
import kms.itemservice.domain.champion.ChampionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/kms/champions")
@RequiredArgsConstructor
public class ChampionController {
    private final ChampionRepository championRepository;

    @GetMapping
    public String champions(Model model){
        List<Champion> findallcham = championRepository.findAll();
        model.addAttribute("champions",findallcham);
        return "/kms/champions";
    }

    @GetMapping("/{championid}")
    public String info(@PathVariable Long championid, Model model){
        Champion findcham = championRepository.findId(championid);
        model.addAttribute("champion",findcham);
        return "/kms/champion";
    }

    @GetMapping("/add")
    public String add(){
        return "kms/chamaddForm";
    }

    @PostMapping("/add")
    public String addCham1(Champion champion, RedirectAttributes redirectAttributes){
        Champion svchampion = championRepository.save(champion);
        redirectAttributes.addAttribute("championId",svchampion.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/kms/champions/{championId}" ;
    }

    //@PostMapping("/add")
    public String addCham(@RequestParam("chamName") String chamName,
                          @RequestParam("price") int price,
                          @RequestParam("comment") String comment,Model model){
        Champion champion = new Champion();
        champion.setComment(comment);
        champion.setChamName(chamName);
        champion.setPrice(price);

        championRepository.save(champion);

        model.addAttribute("champion",champion);

        return "/kms/champion";
    }

    @GetMapping("/{chamId}/edit")
    public String edit(@PathVariable Long chamId, Model model){
        Champion findcham = championRepository.findId(chamId);
        model.addAttribute("champion",findcham);
        return "/kms/editForm";
    }

    @PostMapping("/{chamId}/edit")
    public String editpost(@PathVariable Long chamId,@ModelAttribute Champion champion){
        championRepository.update(chamId,champion);
        return "redirect:/kms/champions/{chamId}";
    }

    /**
     * Test용
     */
    @PostConstruct
    public void Test(){
        championRepository.save(new Champion("vayne",4800,"red glasses"));
        championRepository.save(new Champion("ezreal",6300,"no mu go"));
    }
}
