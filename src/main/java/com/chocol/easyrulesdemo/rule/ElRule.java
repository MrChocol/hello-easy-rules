package com.chocol.easyrulesdemo.rule;

import com.chocol.easyrulesdemo.model.Person;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;

/**
 * @Author: powerchen
 * @Date: 2020/6/2 11:46
 * @Description TODO
 */
public class ElRule {

    public static void main(String[] args) {
        Rule ageRule = new MVELRule()
                .name("age rule")
                .description("Check if person's age is > 18 and marks the person as adult")
                .priority(1)
                .when("person.age > 18")
                .then("person.setAdult(true);");
        Rule ageRule2 = new MVELRule()
                .name("age rule")
                .description("Check if person's age is <= 18 and marks the person as juveniles")
                .priority(2)
                .when("person.age <= 18")
                .then("person.setAdult(false);");
        // create facts
        Facts facts = new Facts();
        Person person = new Person();
        person.setAge(2);
        facts.put("person", person);

        // create rules
        Rules rules = new Rules();
        rules.register(ageRule);
        rules.register(ageRule2);

        // create a rules engine and fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);

        System.out.println(person);
    }

}
