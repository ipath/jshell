package com.ipath.shell;

import org.jline.reader.LineReader;
import org.jline.reader.impl.DefaultHighlighter;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/14 0:03
 * @Version 1.0
 */
public class JshellHighlighter extends DefaultHighlighter {
    public JshellHighlighter() {
        super();
    }

    @Override
    public AttributedString highlight(LineReader reader, String buffer) {
        //AttributedString attributedString = super.highlight(reader, buffer);
        //sb.style(AttributedStyle.DEFAULT.background(3));
        //sb.append("red");

        return new AttributedString(buffer, AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN));
    }
}
