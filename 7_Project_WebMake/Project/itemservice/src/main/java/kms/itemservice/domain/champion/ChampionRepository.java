package kms.itemservice.domain.champion;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChampionRepository {

    //캐시 메모리 사용할 것이므로 HashMap을 사용. 동시성 문제 해결 안되면 권장x
    private static final Map<Long,Champion> store = new HashMap<>();
    private static long sequence = 0L;

    public Champion save(Champion cham){
        cham.setId(++sequence);
        store.put(cham.getId(),cham);
        return cham;
    }

    public Champion findId(Long id){
        return store.get(id);
    }

    public List<Champion> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long id, Champion cham){
        Champion origincham = findId(id);
        origincham.setChamName(cham.getChamName());
        origincham.setPrice(cham.getPrice());
        origincham.setComment(cham.getComment());
    }

    public void clearAll(){
        store.clear();
    }


}
