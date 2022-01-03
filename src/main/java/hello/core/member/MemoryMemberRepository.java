package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository { // 구현체 생성 , 인터페이스에 implements 사용

    private static Map<Long, Member> store = new HashMap<>(); // 저장소 만듬

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return null;
    }
}
