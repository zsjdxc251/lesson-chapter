package com.chapter.microservice.application.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

import java.util.concurrent.Executor;

/**
 * @author zhengshijun
 * @version created on 2019/9/7.
 */
public class CustomApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    @Nullable
    private Executor taskExecutor;

    @Override
    public void multicastEvent(ApplicationEvent event) {

        multicastEvent(event, resolveDefaultEventType(event));

    }

    @Override
    public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {
        ResolvableType type = (eventType != null ? eventType : resolveDefaultEventType(event));
        for (final ApplicationListener<?> listener : getApplicationListeners(event, type)) {
            Executor executor = getTaskExecutor();
            if (executor != null) {
                executor.execute(() -> invokeListener(listener, event));
            } else {
                invokeListener(listener, event);
            }
        }
    }

    private ResolvableType resolveDefaultEventType(ApplicationEvent event) {
        return ResolvableType.forInstance(event);
    }

    public void setTaskExecutor(@Nullable Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    /**
     * Return the current task executor for this multicaster.
     */
    @Nullable
    protected Executor getTaskExecutor() {
        return this.taskExecutor;
    }

    /**
     * Invoke the given listener with the given event.
     *
     * @param listener the ApplicationListener to invoke
     * @param event    the current event to propagate
     * @since 4.1
     */
    protected void invokeListener(ApplicationListener<?> listener, ApplicationEvent event) {
        doInvokeListener(listener, event);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void doInvokeListener(ApplicationListener listener, ApplicationEvent event) {
        try {
            listener.onApplicationEvent(event);
        } catch (ClassCastException ex) {
            String msg = ex.getMessage();
            if (msg == null || matchesClassCastMessage(msg, event.getClass())) {
                // Possibly a lambda-defined listener which we could not resolve the generic event type for
                // -> let's suppress the exception and just log a debug message.
                Log logger = LogFactory.getLog(getClass());
                if (logger.isDebugEnabled()) {
                    logger.debug("Non-matching event type for listener: " + listener, ex);
                }
            } else {
                throw ex;
            }
        }
    }

    private boolean matchesClassCastMessage(String classCastMessage, Class<?> eventClass) {
        // On Java 8, the message starts with the class name: "java.lang.String cannot be cast..."
        if (classCastMessage.startsWith(eventClass.getName())) {
            return true;
        }
        // On Java 11, the message starts with "class ..." a.k.a. Class.toString()
        if (classCastMessage.startsWith(eventClass.toString())) {
            return true;
        }
        // On Java 9, the message used to contain the module name: "java.base/java.lang.String cannot be cast..."
        int moduleSeparatorIndex = classCastMessage.indexOf('/');
        if (moduleSeparatorIndex != -1 && classCastMessage.startsWith(eventClass.getName(), moduleSeparatorIndex + 1)) {
            return true;
        }
        // Assuming an unrelated class cast failure...
        return false;
    }
}
