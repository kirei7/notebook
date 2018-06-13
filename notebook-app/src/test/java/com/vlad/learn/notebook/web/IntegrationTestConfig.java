package com.vlad.learn.notebook.web;

import com.vlad.learn.notebook.core.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreConfig.class)
public class IntegrationTestConfig {
}
