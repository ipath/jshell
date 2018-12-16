package com.ipath;

import com.ipath.shell.JshellHighlighter;
import org.jline.reader.Highlighter;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/13 21:28
 * @Version 1.0
 */
public class Example1 {
    public static void main(String[] args) {
        try {
            StringsCompleter completer = new StringsCompleter("create", "open", "write","save","spring");
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            Highlighter highlighter = new JshellHighlighter();
            LineReader lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(completer)
                    .appName("jshell-dp")
                    .highlighter(highlighter)
                    .build();
            String prompt = "jshell>";
            AttributedString attributedString = new AttributedString(prompt, AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));

            while(true){
                String line;
                line = lineReader.readLine(attributedString.toAnsi());
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
