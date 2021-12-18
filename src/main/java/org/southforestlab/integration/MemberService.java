package org.southforestlab.integration;
import java.util.Collection;

/**
 * Service interface for managing members.
 */
public interface MemberService {

    /**
     * Find a member by the given ID
     *
     * @param id
     *            the ID of the member
     * @return the member, or <code>null</code> if member not found.
     */
    Member findMember(Integer id);

    /**
     * Find all members
     *
     * @return a collection of all members
     */
    Collection<Member> findMembers();

    /**
     * Update the given member
     *
     * @param member
     *            the member
     */
    void updateMember(Member member);

}