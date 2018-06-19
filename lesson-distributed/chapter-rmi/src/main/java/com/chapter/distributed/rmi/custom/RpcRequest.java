package com.chapter.distributed.rmi.custom;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class RpcRequest implements Serializable{


    private static final long serialVersionUID = -9100893052391757993L;
    private String className;

    private String methodName;

    private Object[] parameters;

    public RpcRequest() {
    }

    public RpcRequest(String className, String methodName, Object[] parameters) {
        this.className = className;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
