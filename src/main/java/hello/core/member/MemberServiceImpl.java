package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 구현객체 선택

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class) // 의존관계 자동 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 생성자를 통해서 MemberRepository에 뭐가 들어갈지 정한다
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member); // join에서 save를 호출하면 다형성에 의해서 MemoryMemberRespository에 있는 save 호출
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
