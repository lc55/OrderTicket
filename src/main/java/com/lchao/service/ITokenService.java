package com.lchao.service;


import com.lchao.common.Token;
import com.lchao.enums.TokenType;


public interface ITokenService {

    String addToken(Token token);

    Token checkToken(String token, TokenType tokenType);

    void deleteTokenByUserId(Integer userId);
    void deleteTokenByUserId(Integer userId,TokenType tokenType);

}
