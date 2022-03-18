package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 동시성 문제가 고려되어 있지 않아, 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values()); // store에 저장된 값들을 직접적으로 건드리지 않기위해 ArrayList에 담음
    }

    //테스트용 : store를 모두 삭제
    public void clearStore(){
        store.clear();
    }
}
