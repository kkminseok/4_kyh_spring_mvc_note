package hello.springmvc.basic.reqeustmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kms-mapping/champions")
public class ChampionController {

    /**
     * GET /kms-mapping/champions
     */
    @GetMapping
    public String champions(){
        return "get champions";
    }

    /**
     * POST /kms-mapping/champions
     */
    @PostMapping
    public String addChampions(){
        return "post champions";
    }

    /**
     * GET /kms-mapping/champions/{champions}
     */
    @GetMapping("/{championId}")
    public String findChampion(@PathVariable String championId){
        return "get championId :" + championId;
    }

    /**
     * PATCH /kms-mapping/champions/{champions}
     */
    @PatchMapping("/{championId}")
    public String updateChampion(@PathVariable String championId){
        return "patch championId : " + championId;
    }

    /**
     * DELETE /kms-mapping
     */
    @DeleteMapping("/{championId}")
    public String deleteChampion(@PathVariable String championId){
        return "delete championId : " + championId;
    }
}
