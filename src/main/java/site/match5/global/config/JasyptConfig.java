package site.match5.global.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Value("${jasypt.encryptor.password}") // 비공개 키를 프로그램에서 하드 코딩하지 않고 외부로부터 주입
    private String password;

    @Bean("jasyptStringEncryptor") //빈 이름은 기본 값을 사용
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password); // 비공개 키를 프로그램에서 하드 코딩하지 않고 외부로부터 주입
        config.setAlgorithm("PBEWithMD5AndDES"); // 암호 알고리즘은 편의상 기본 값이 아닌 PBEWithMD5AndDES 방식을 사용
        config.setPoolSize("1"); // 풀 사이즈의 기본 값은 1이라고 문서에 적혀 있지만, 실제로 값이 없는 경우 예외가 발생하므로 1을 지정
        encryptor.setConfig(config);
        return encryptor;
    }
}
