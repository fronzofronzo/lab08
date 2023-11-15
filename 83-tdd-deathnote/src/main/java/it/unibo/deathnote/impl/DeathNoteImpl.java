package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    private final Map<String, Pair<String,String>> deaths = new HashMap<>();
    private long time ;

    public DeathNoteImpl() {
    }
    
    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Input number not valid");
        }
        return RULES.get(ruleNumber);
    }

    @Override
    public void writeName(String name) {
        if()
    }

    @Override
    public boolean writeDeathCause(String cause) {
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

    private class Pair<X,Y> {

        private  X first;
        private  Y second;

        public Pair(X first, Y second) {
            this.first = first;
            this.second = second;
        }

        public X getFirst () {
            return this.first;
        }

        public Y getSecond() {
            return this.second;
        }

        public void setFirst(X input) {
            this.first = input;
        }

        public void setSecond(Y input) {
            this.second = input;
        }
    }

}