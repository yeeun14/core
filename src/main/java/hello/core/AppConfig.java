package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig { // 구현객체 생성

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); // 생서자를 통해서 객체가 들어간다고 해, 생성자 주입이라고 함
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
