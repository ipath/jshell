package com.ipath.example;

import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/15 11:58
 * @Version 1.0
 */
public class AttributeString {
    @Test
    public void testColor(){

        String ansiStr = new AttributedStringBuilder()
                .append("This i")
                .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE))
                .append("s")
                .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW))
                .append(" a")
                .style(AttributedStyle.DEFAULT.foreground(AttributedStyle.RED))
                .append(" Test.")
                .toAnsi();
        assertEquals("This i\u001B[34ms\u001B[33m a\u001B[31m Test.\u001B[0m", ansiStr);
    }
}
