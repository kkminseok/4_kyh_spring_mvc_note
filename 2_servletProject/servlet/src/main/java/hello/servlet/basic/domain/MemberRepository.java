package hello.servlet.basic.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.pow;

public class MemberRepository {

    //저장소
    private static final Map<Long,Member> store = new HashMap<>();
    //index가 증가할수록 1씩 늘어남.
    private static long sequence = 0L;

    //싱글톤 기법으로 객체 생성
    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository(){}

    public static MemberRepository getInstance(){
        return instance;
    }

    //Method

    /*
    멤버 저장
     */
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    /*
    단일 멤버 조회
     */
    public Member findById(Long id){
        return store.get(id);
    }

    /*
    전체 멤버 조회
     */
    public List<Member> findByAll(){
        return new ArrayList<>(store.values());
    }

    /*
    저장소 삭제
     */
    public void clearStore(){
        store.clear();
    }


}
