package com.example.spring_intro.service;

import com.example.spring_intro.domain.Member;
import com.example.spring_intro.repository.MemberRepository;
import com.example.spring_intro.repository.MemoryMemberRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Business Requirement: No duplicate names allowed
    public Member join(Member member) {
        extracted(member);

        memberRepository.save(member);
        return member;
    }

    private void extracted(@NonNull Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("A member with that name already exists.");
            });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
