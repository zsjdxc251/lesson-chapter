package com.chapter.distributed.rmi.custom.dubbo.client;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/6/26.
 */
public class RpcInvocation implements Invocation {

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] arguments;

    private Map<String, String> attachments;

    private transient Invoker<?> invoker;

    public RpcInvocation() {
    }

    public RpcInvocation(Invocation invocation, Invoker<?> invoker) {
        this(invocation.getMethodName(), invocation.getParameterTypes(),
                invocation.getArguments(), new HashMap<String, String>(invocation.getAttachments()),
                invocation.getInvoker());

    }

    public RpcInvocation(Invocation invocation) {
        this(invocation.getMethodName(), invocation.getParameterTypes(),
                invocation.getArguments(), invocation.getAttachments(), invocation.getInvoker());
    }

    public RpcInvocation(Method method, Object[] arguments) {
        this(method.getName(), method.getParameterTypes(), arguments, null, null);
    }

    public RpcInvocation(Method method, Object[] arguments, Map<String, String> attachment) {
        this(method.getName(), method.getParameterTypes(), arguments, attachment, null);
    }

    public RpcInvocation(String methodName, Class<?>[] parameterTypes, Object[] arguments) {
        this(methodName, parameterTypes, arguments, null, null);
    }

    public RpcInvocation(String methodName, Class<?>[] parameterTypes, Object[] arguments, Map<String, String> attachments) {
        this(methodName, parameterTypes, arguments, attachments, null);
    }

    public RpcInvocation(String methodName, Class<?>[] parameterTypes, Object[] arguments, Map<String, String> attachments, Invoker<?> invoker) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes == null ? new Class<?>[0] : parameterTypes;
        this.arguments = arguments == null ? new Object[0] : arguments;
        this.attachments = attachments == null ? new HashMap<String, String>() : attachments;
        this.invoker = invoker;
    }

    public Invoker<?> getInvoker() {
        return invoker;
    }

    public void setInvoker(Invoker<?> invoker) {
        this.invoker = invoker;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes == null ? new Class<?>[0] : parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments == null ? new Object[0] : arguments;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments == null ? new HashMap<String, String>() : attachments;
    }

    public void setAttachment(String key, String value) {
        if (attachments == null) {
            attachments = new HashMap<String, String>();
        }
        attachments.put(key, value);
    }

    public void setAttachmentIfAbsent(String key, String value) {
        if (attachments == null) {
            attachments = new HashMap<String, String>();
        }
        if (!attachments.containsKey(key)) {
            attachments.put(key, value);
        }
    }

    public void addAttachments(Map<String, String> attachments) {
        if (attachments == null) {
            return;
        }
        if (this.attachments == null) {
            this.attachments = new HashMap<String, String>();
        }
        this.attachments.putAll(attachments);
    }

    public void addAttachmentsIfAbsent(Map<String, String> attachments) {
        if (attachments == null) {
            return;
        }
        for (Map.Entry<String, String> entry : attachments.entrySet()) {
            setAttachmentIfAbsent(entry.getKey(), entry.getValue());
        }
    }

    public String getAttachment(String key) {
        if (attachments == null) {
            return null;
        }
        return attachments.get(key);
    }

    public String getAttachment(String key, String defaultValue) {
        if (attachments == null) {
            return defaultValue;
        }
        String value = attachments.get(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        return value;
    }

    @Override
    public String toString() {
        return "RpcInvocation [methodName=" + methodName + ", parameterTypes="
                + Arrays.toString(parameterTypes) + ", arguments=" + Arrays.toString(arguments)
                + ", attachments=" + attachments + "]";
    }
}
