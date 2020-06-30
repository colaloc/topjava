package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class MyTestRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new MyStatement(base, description);
    }

    static class MyStatement extends Statement {
        private static final Logger log = LoggerFactory.getLogger(MyStatement.class);
        private final Statement base;
        private final Description description;
        private final StopWatch timer = new StopWatch();

        public MyStatement(Statement base, Description description) {
            this.base = base;
            this.description = description;
        }

        @Override
        public void evaluate() throws Throwable {
            timer.start(description.getMethodName());
            base.evaluate();
            timer.stop();
            String timerResult = timer.getLastTaskName() + " - " + timer.getLastTaskTimeMillis() + "ms";
            log.info(timerResult);
            MyClassRule.timers.add(timerResult);
        }
    }
}
