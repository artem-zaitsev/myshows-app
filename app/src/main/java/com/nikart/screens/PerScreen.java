package com.nikart.screens;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Artem on 28.05.2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerScreen {
}
