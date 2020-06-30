package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
        }

        @Override
        public void evaluate() throws Throwable {
            base.evaluate();
            timers.forEach(log::info);
        }
    }
}
