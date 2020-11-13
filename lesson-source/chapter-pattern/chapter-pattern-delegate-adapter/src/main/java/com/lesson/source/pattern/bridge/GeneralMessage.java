package com.lesson.source.pattern.bridge;

/**
 * @author zhengshijun
 * @version created on 2020/4/19.
 */
public class GeneralMessage extends AbstractMessage {
    protected GeneralMessage(IMessage message) {
        super(message);
    }

    @Override
    public void sendMessage(String toUser, String message) {
        super.sendMessage(toUser, "普通："+message);
    }
}
