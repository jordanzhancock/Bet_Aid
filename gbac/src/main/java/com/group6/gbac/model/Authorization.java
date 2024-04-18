package com.group6.gbac.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Authorization {

    static List<UUID> tokenList = new ArrayList<UUID>();

    public static UUID createToken(){
        UUID accessToken = UUID.randomUUID();
        tokenList.add(accessToken);
        return accessToken;
    }

    public static Boolean validateToken(UUID accessToken){
        return tokenList.contains(accessToken);
    }

    public static Boolean removeToken(UUID accessToken){
        if(tokenList.contains(accessToken)){
            tokenList.remove(accessToken);
            return true;
        } else {
            return false;
        }
    }
}
