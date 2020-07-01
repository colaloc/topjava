package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyClassRule implements TestRule {

    public static List<String> timers = new ArrayList<>();

    @Override
    public Statement apply(Statement base, Description description) {
        return new MyStatement(base);
    }

    static class MyStatement extends Statement {
        private static final Logger log = LoggerFactory.getLogger(MyStatement.class);
        private final Statement base;

        public MyStatement(Statement base) {
            this.base = base;
            timers.clear();
        }

        @Override
        public void evaluate() throws Throwable {
            base.evaluate();
            //timers.forEach(log::info);
            log.info(toTableView());
        }

        private String toTableView() {
            StringBuilder stringBuilder = new StringBuilder();
            int max = timers.stream().max(Comparator.comparingInt(String::length)).get().length();
            for (String s : timers) {
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append(s.split(" - ")[0]);
                for (int i = 0; i < max + 3 - s.length(); i++)
                    stringBuilder.append(" ");
                stringBuilder.append(s.split(" - ")[1]);
            }
            return stringBuilder.toString();
        }
    }
}
