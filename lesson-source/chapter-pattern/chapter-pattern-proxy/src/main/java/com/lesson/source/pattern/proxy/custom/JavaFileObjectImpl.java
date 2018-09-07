package com.lesson.source.pattern.proxy.custom;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * @author zhengshijun
 * @version created on 2018/9/7.
 */
public class JavaFileObjectImpl extends SimpleJavaFileObject {
    private final CharSequence source;
    private ByteArrayOutputStream bytecode;
    public JavaFileObjectImpl(final String baseName, final CharSequence source) throws Exception {
        super(new URI(baseName+".java"), Kind.SOURCE);
        this.source = source;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        if (source == null) {
            throw new UnsupportedOperationException("source == null");
        }
        return source;
    }

    @Override
    public InputStream openInputStream() {
        return new ByteArrayInputStream(getByteCode());
    }

    @Override
    public OutputStream openOutputStream() {
        return bytecode = new ByteArrayOutputStream();
    }

    public byte[] getByteCode() {
        return bytecode.toByteArray();
    }
}
