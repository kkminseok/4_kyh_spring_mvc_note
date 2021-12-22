package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.basic.domain.Member;
import hello.servlet.basic.domain.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        //요청 정보를 가져옴.
        String username = paramMap.get("username");
        int height = Integer.parseInt(paramMap.get("height"));
        int weight = Integer.parseInt(paramMap.get("weight"));

        Member member = new Member(username,height,weight);

        memberRepository.save(member);

        model.put("member",member);
        return "save-result";
    }
}
