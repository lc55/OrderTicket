package com.lchao.service;


import com.lchao.common.Token;
import com.lchao.enums.TokenType;


public interface ITokenService {

    String addToken(Token token);

    void deleteTokenByUserId(Integer userId,TokenType tokenType);

}
