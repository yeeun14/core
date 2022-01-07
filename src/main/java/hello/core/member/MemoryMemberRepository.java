package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository { // 구현체 생성 , 인터페이스에 implements 사용

    private static Map<Long, Member> store = new HashMap<>(); // 저장소 만듬, HahsMap은 동시성 이슈가 있을 수 있음

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return  store.get(memberId);
    }
}
