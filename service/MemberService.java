package likelion.springbootnyong.service;

import likelion.springbootnyong.domain.Member;

import java.util.List;

public interface MemberService {
    public void save(Member member);

    public Member findById(Long id);
    public List<Member> findAll();
}
