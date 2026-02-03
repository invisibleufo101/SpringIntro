package com.example.spring_intro.repository;

import com.example.spring_intro.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // AfterEach is an annotation for a callback method that will **reset**
    // whatever needs to be reset in order to have a clean slate for the next testcase
    @AfterEach
    public void afterEach() {
        //
        repository.clearStore();
    }

    @Test
    public void save() {
        // Create new member
        Member member = new Member();
        // Set name to member
        member.setName("spring");

        repository.save(member);
        Long memberId = member.getId();
        Member foundMember = repository.findById(memberId).get();

        assertThat(member).isEqualTo(foundMember);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("test member 1");

        Member member2 = new Member();
        member2.setName("test member 2");

        repository.save(member1);
        repository.save(member2);

        Member foundMember1 = repository.findByName("test member 1").get();
        Member foundMember2 = repository.findByName("test member 2").get();

        assertThat(foundMember1).isNotSameAs(foundMember2);
    }

    @Test
    public void findAll() {
        // Add temporary members to repository
        for (int i=0; i<3; i++) {
            Member member = new Member();
            member.setName("member " + String.valueOf(i));
            repository.save(member);
        }

        List<Member> foundMembers = repository.findAll();
        assertThat(foundMembers.size()).isEqualTo(3);
    }

}
