package com.example.spring_intro.config;


import com.example.spring_intro.repository.MemberRepository;
import com.example.spring_intro.repository.MemoryMemberRepository;
import com.example.spring_intro.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // This feels very similar to how I was also registering dependencies in SlimPHP

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

}
