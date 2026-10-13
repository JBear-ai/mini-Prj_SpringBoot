package com.Human.mini_Prj.Exception;

public class CustomException extends RuntimeException {

    // 오류 코드와 메시지를 보관하고 전달하는 역할

    private final String code;

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}