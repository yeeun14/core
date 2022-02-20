package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 필수 생성자 생성
public class OrderServiceImpl implements OrderService { // 주문 생성 요청

    private final MemberRepository memberRepository; // final은 별도의 생성자를 만들지 못한다.
    private final DiscountPolicy discountPolicy; // DIP 지키는 예제
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // DIP 위반
//    private DiscountPolicy discountPolicy; //구체에 의존하지 않고 인터페이스에만 의존한다. / NPE 발생

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자 필요없음, 밑에 코드 없애도 됨 단, 기존코드때문에 컴파일 오류가 남
    //new OrderServiceImpl(memberRepository, discountPolicy);
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { //@RequiredArgsConstructor로 생성
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
//        System.out.println("1. OrderServiceImpl.OrderServiceimpl");
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
