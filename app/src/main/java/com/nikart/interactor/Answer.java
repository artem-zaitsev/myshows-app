package com.nikart.interactor;

import android.support.annotation.Nullable;

/**
 * Created by Artem on 09.03.2017.
 */

public class Answer {

    @Nullable
    private Object answer;

    @Nullable
    public <T> T getTypedAnswer() {
        if (answer == null) {
            return null;
        }

        return (T) answer;
    }

    public void setAnswer(@Nullable Object answer) {
        this.answer = answer;
    }

}
