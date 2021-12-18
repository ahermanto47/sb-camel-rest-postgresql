package org.southforestlab.integration;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    private final Map<Integer, Member> members = new TreeMap<>();

    public MemberServiceImpl() {
    	members.put(1, new Member(1, "John Coltrane"));
    	members.put(2, new Member(2, "Miles Davis"));
    	members.put(3, new Member(3, "Sonny Rollins"));
    }

    @Override
    public Member findMember(Integer id) {
        return members.get(id);
    }

    @Override
    public Collection<Member> findMembers() {
        return members.values();
    }

    @Override
    public void updateMember(Member member) {
    	members.put(member.getId(), member);
    }

}