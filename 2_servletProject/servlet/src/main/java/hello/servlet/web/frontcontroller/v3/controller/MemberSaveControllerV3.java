package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.basic.domain.Member;
import hello.servlet.basic.domain.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String,String> paramMap){
        //요청 정보를 가져옴.
        String username = paramMap.get("username");
        int height = Integer.parseInt(paramMap.get("height"));
        int weight = Integer.parseInt(paramMap.get("weight"));

        Member member = new Member(username,height,weight);

        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
