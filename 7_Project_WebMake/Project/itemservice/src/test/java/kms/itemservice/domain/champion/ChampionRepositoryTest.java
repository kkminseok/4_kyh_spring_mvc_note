package kms.itemservice.domain.champion;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChampionRepositoryTest {

    ChampionRepository championRepository = new ChampionRepository();

    //테스트 이후 저장소 초기화
    @AfterEach
    void clear(){
        championRepository.clearAll();
    }

    @Test
    void save() {
        //Given
        Champion champion = new Champion("vayne",4800,"빨간안경");
        //When
        championRepository.save(champion);

        //Then
        Champion findcham = championRepository.findId(champion.getId());
        Assertions.assertThat(findcham).isEqualTo(champion);
    }

    @Test
    void findAll() {
        //Given
        Champion vayne = new Champion("vayne",4800,"빨간안경");
        Champion Ezreal = new Champion("Ezreal",6300,"노머고");
        //When
        championRepository.save(vayne);
        championRepository.save(Ezreal);
        List<Champion> allcham = championRepository.findAll();
        //Then
        Assertions.assertThat(allcham.size()).isEqualTo(2);
        Assertions.assertThat(allcham).contains(vayne,Ezreal);

    }

    @Test
    void update() {
        //Given
        Champion champion = new Champion("vayne",4800,"빨간안경");
        Champion vayne = championRepository.save(champion);
        Long vayneid = vayne.getId();

        //When
        Champion updatecham = new Champion("Ezreal",6300,"노머고");
        championRepository.update(vayneid,updatecham);

        Champion ezreal = championRepository.findId(vayneid);
        //Then
        Assertions.assertThat(ezreal.getChamName()).isEqualTo(updatecham.getChamName());
        Assertions.assertThat(ezreal.getPrice()).isEqualTo(updatecham.getPrice());
        Assertions.assertThat(ezreal.getComment()).isEqualTo(updatecham.getComment());

    }
}