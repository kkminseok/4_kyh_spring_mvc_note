package hello.servlet.domain;

import hello.servlet.basic.domain.Member;
import hello.servlet.basic.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    //테스트를 끝나고 저장소를 초기화
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member= new Member("kms",178,74);
        //when
        Member savemember = memberRepository.save(member);
        //then
        Member findmember = memberRepository.findById(savemember.getId());

        Assertions.assertThat(savemember).isEqualTo(findmember);
    }

    @Test
    void findbyAll(){
        //given
        Member user1 = new Member("kms", 178, 74);
        Member user2 = new Member("jyb", 160, 46);

        memberRepository.save(user1);
        memberRepository.save(user2);

        //when
        List<Member> members = memberRepository.findByAll();
        System.out.println(user2.getBmi());
        //then
        Assertions.assertThat(members.size()).isEqualTo(2);
        Assertions.assertThat(members).contains(user1,user2);
    }
}
