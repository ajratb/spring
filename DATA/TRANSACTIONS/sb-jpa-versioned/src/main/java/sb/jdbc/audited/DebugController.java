package sb.jdbc.audited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DebugController {

    private final UserRepository userRepository;

    @GetMapping("/test")
    void test(){
        log.info("TEST");
        MyUser serg = userRepository.save(new MyUser("Serg"));
        serg.setName("New Serg");
        // org.springframework.dao.OptimisticLockingFailureException:
        // Optimistic lock exception on saving entity of type sb.jdbc.audited.MyUser
        // serg.setVer(3);
        userRepository.save(serg);
    }
}
