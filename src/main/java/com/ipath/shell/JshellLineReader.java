package com.ipath.shell;

import com.ipath.core.container.Dispatch;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

import java.io.IOException;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/15 16:35
 * @Version 1.0
 */
public class JshellLineReader {
    public JshellLineReader(){

    }

    public static LineReader builder(){
        LineReader lineReader = null;
        try {
            StringsCompleter completer = new StringsCompleter(JshellStringsCompleter.commands);
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(completer)
                    .appName("jshell-dp")
                    .highlighter(new JshellHighlighter())
                    .build();
            String prompt = "jshell>";
            AttributedString attributedString = new AttributedString(prompt, AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));

            while(true){
                String linebuffer = lineReader.readLine(attributedString.toAnsi());
                Object o = Dispatch.doDispatch(linebuffer);
                if( o == null ){
                    o = "";
                }
                System.out.println(o.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return lineReader;
    }
}
