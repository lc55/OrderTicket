package com.lchao.service;


import com.lchao.common.Result;
import com.lchao.common.Token;
import com.lchao.enums.UserType;


public interface ITokenService {

    Result addToken(Token token);

    void deleteTokenByUserId(Integer userId, UserType tokenType);

    Token getTokenByType(String tokenKey, UserType userType);

}
