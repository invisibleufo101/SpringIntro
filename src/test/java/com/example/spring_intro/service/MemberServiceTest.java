package com.example.spring_intro.service;

import com.example.spring_intro.domain.Member;
import com.example.spring_intro.repository.MemberRepository;
import com.example.spring_intro.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    void beforeEach() {
        MemoryMemberRepository repository = new MemoryMemberRepository();
        MemberService memberService = new MemberService(repository);
    }

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }
    /**
     * Testing logic for creating new members (when new user joins)
     */
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("test member");

        // when
        Member newMember = memberService.join(member);
        Long newMemberId = newMember.getId();

        // then
        Member findMember = memberService.findOne(newMemberId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void joinOnDuplicateName() {
        // Given - 2 members with duplicate name
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("test member1");
        member2.setName("test member1");

        // When - A member with the name already joined
        memberService.join(member1);

        // Then - Throw IllegalStateException when a member with duplicate name tries to join
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("A member with that name already exists.");
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}