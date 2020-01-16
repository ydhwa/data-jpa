package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.UsernameOnlyDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
public class ProjectionTest {

    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;


    @Test
    public void projection() {
        // given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        em.persist(new Member("m1", 0, teamA));
        em.persist(new Member("m2", 0, teamA));
        em.flush();
        em.clear();

        // when
//        List<UsernameOnly> result = memberRepository.findProjectionsByUsername("m1");
//
//        for (UsernameOnly usernameOnly : result) {
//            System.out.println("usernameOnly = " + usernameOnly.getUsername());
//        }

//        List<UsernameOnlyDto> result = memberRepository.findProjectionsDtoByUsername("m1", UsernameOnlyDto.class);
//        for (UsernameOnlyDto usernameOnlyDto : result) {
//            System.out.println("usernameOnlyDto = " + usernameOnlyDto.getUsername());
//        }

        List<NestedClosedProjections> result = memberRepository.findProjectionsDtoByUsername("m1", NestedClosedProjections.class);
        for (NestedClosedProjections nestedClosedProjections : result) {
            System.out.println("nestedClosedProjections.getUsername() = " + nestedClosedProjections.getUsername());
            System.out.println("nestedClosedProjections.getTeam().getName() = " + nestedClosedProjections.getTeam().getName());
        }
    }
}
