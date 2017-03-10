package com.nikart.base;

import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Created by Artem on 09.03.2017.
 */

public class Response {

    @Nullable
    private Object answer;

    public <T> T getTypedAnswer() {
        if(answer == null ) {
            return null;
        }

        return(T) answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

}
