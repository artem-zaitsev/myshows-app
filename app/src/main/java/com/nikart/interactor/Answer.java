package com.nikart.interactor;

import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Created by Artem on 09.03.2017.
 */

public class Answer {

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
