package com.lesson.source.pattern.bridge;

/**
 * @author zhengshijun
 * @version created on 2020/4/19.
 */
public class UrgentMessage extends AbstractMessage {
    protected UrgentMessage(IMessage message) {
        super(message);
    }

    @Override
    public void sendMessage(String toUser, String message) {
        super.sendMessage(toUser,"加急："+ message);
    }
}
