package com.lesson.source.pattern.builder;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class OperateFileFactory {

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{

        private String prefix;

        private String username;

        private String password;

        private String host;

        private Integer port;

        public OperateFile builder(){
            return new OperateFileImpl(this);
        }

        public Builder authorization(String username,String password) {
            this.username = username;
            this.password = password;
            return this;
        }
        public Builder connect(String host,Integer port) {
            this.host = host;
            this.port = port;
            return this;
        }

        public Builder namespace(String prefix){
            this. prefix = prefix;
            return this;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }
}
