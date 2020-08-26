package com.myapi.auth;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.ArrayList;
import edu.umd.cs.findbugs.annotations.Nullable;

@Singleton
public class MyAuthProvider implements AuthenticationProvider {

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> re, AuthenticationRequest<?, ?> req) {
        String username = req.getIdentity().toString();
        String password = req.getSecret().toString();
        return Flowable.create(em -> {
            if (password.equals("password")) {
                Collection<String> col = new ArrayList<String>();
                col.add("ADMIN");
                UserDetails details = new UserDetails(username, col);
                em.onNext(details);
                em.onComplete();
            } else {
                em.onError(new AuthenticationException(new AuthenticationFailed()));
            }
        }, BackpressureStrategy.ERROR);

        // if (password.equals("password")) {
        //     Collection<String> col = new ArrayList<String>();
        //     col.add("ADMIN");
        //     UserDetails details = new UserDetails(username, col);
        //     return Flowable.just(details);
        // } else {
        //     return Flowable.just(new AuthenticationFailed());
        // }
    }
}