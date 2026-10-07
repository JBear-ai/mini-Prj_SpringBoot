package com.Human.mini_Prj.Controller;

import com.Human.mini_Prj.Dto.LoginReqDto;
import com.Human.mini_Prj.Dto.SignUpReqDto;
import com.Human.mini_Prj.Service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;

@Slf4j
@RestController     // RestFull Api 직렬화/역직렬화 자동화
@RequestMapping     // 접근경로  http://localhost:8111/auth/
@RequiredArgsConstructor


public class AuthController {
    private final AuthService authService; // 생성자를 통한 의존성 주입

    // 회원 가입 여부 확인
    @GetMapping("/exits/{email}")
    public boolean existsEmail(@PathVariable String email)
    {
        return authService.isMember(email);
    }

    // 회원 가입
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@RequestBody SignUpReqDto dto){
        return ResponseEntity.ok(authService.signUp(dto));
    }


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginReqDto dto){
        return ResponseEntity.ok(authService.login(dto.getEmail(),dto.getPwd()));
    }
}
