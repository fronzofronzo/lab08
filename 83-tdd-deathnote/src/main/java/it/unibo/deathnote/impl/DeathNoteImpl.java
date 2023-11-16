package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    private final Map<String, Pair<String,String>> deaths = new HashMap<>();
    private long time ;
    private String actualName = null;

    public DeathNoteImpl() {
    }

    private void setTime(long time) {
        this.time = time;
    }

    private void setActualName(String name) {
        this.actualName = name;
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
        if(name == null) {
            throw new NullPointerException("Name insered is null");
        }
        deaths.put(name, new Pair<String,String>("", ""));
        setActualName(name);
        setTime(System.currentTimeMillis());
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(this.actualName == null || cause == null ) {
            throw new IllegalStateException(cause == null ? "Cause is null " : "There's no name written in the DeathNote ");
        }
        final long actualTime = System.currentTimeMillis();
        if((actualTime - time) < 40) {
            deaths.get(actualName).setFirst(cause);
            setTime(System.currentTimeMillis());
            return true;
        } else {
            setActualName(null);
            return false;
        }
    }

    @Override
    public boolean writeDetails(String details) {
       if(this.actualName == null || details == null ) {
            throw new IllegalStateException(details == null ? "Details are null " : "There's no name written in the DeathNote ");
        }
        final long actualTime = System.currentTimeMillis();
        if((actualTime - time) < 6040) {
            deaths.get(actualName).setSecond(details);
            setActualName(null);
            setTime(System.currentTimeMillis());
            return true;
        } else {
            setActualName(null);
            return false;
        }
    }

    @Override
    public String getDeathCause(String name) {
        if(!deaths.containsKey(name)) {
            throw new IllegalArgumentException("There's no name in the DeathNote ");
        }
        String s = deaths.get(name).getFirst();
        if(s == "") {
            return "heart attack";
        } else {
            return s;
        }
    }

    @Override
    public String getDeathDetails(String name) {
        if(!deaths.containsKey(name)) {
            throw new IllegalArgumentException("There's no name in the DeathNote ");
        }
        String s = deaths.get(name).getSecond();
        if(s == "") {
            return "heart attack";
        } else {
            return s;
        }
    }

    @Override
    public boolean isNameWritten(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

    /**
     * A class that implements a Pair of two objects. First is of the X type
     * and the second is of the Y type
     */
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