package sb.jdbc.audited;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class SbJdbcAuditedApplication {//implements CommandLineRunner {

	private final MyUserRepository userRepository;

	//@Override
	public void run(String... args) throws Exception {
		MyUser myUser = new MyUser();
//		myUser.setId(4);
		myUser.setName("Vasya");
		//myUser.setVer(6);
		userRepository.save(myUser);
		MyUser found = userRepository.findById(1).get();
		found.setName("New Vasya");
		MyUser updated = userRepository.save(found);
		updated.setName("Vasya ver.2");
		userRepository.save(updated);
	}

	public static void main(String[] args) {
		SpringApplication.run(SbJdbcAuditedApplication.class, args);
	}
}
