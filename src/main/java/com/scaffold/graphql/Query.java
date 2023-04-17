package com.scaffold.graphql;

import com.scaffold.graphql.types.Demo;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Query {

    @QueryMapping
    public Demo getTheDemoObject(@Argument String name) {
        return new Demo("Jane", 18);
    }
}
