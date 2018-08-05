package com.chapter.microservice.boot.custom.bootstrap;

import com.chapter.microservice.boot.api.manager.ActionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ActionManager.class)
public class BootstrapConfigure {
}
