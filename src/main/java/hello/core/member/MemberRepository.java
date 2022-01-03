package hello.core.member;

public interface MemberRepository { // 인터페이스 설정

    void save(Member member);

    Member findById(Long memberId);
}
