package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService { // 주문 생성 요청

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // DIP 지키는 예제
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // DIP 위반
//    private DiscountPolicy discountPolicy; //구체에 의존하지 않고 인터페이스에만 의존한다. / NPE 발생


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에 멤버정보 전달

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도도
   public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
