package it.unibo.deathnote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    final private static int NEGATIVE_NUM = -1;

    final DeathNote testDeathNote = new DeathNoteImpl();

    @Test
    public void testNumRulesNotZero() {
        try {
            testDeathNote.getRule(0);
            Assertions.fail();
        } catch (IllegalArgumentException e ) {
            Assertions.assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testNumRulesNotNegative() {
        try {
            testDeathNote.getRule(NEGATIVE_NUM);
            Assertions.fail();
        } catch (IllegalArgumentException e ) {
            Assertions.assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testNoRuleIsNull() {
        final int numRules = 10;
        for(int i = 1; i< numRules; i++) {
            Assertions.assertNotNull(testDeathNote.getRule(i));
        }
    }

    @Test
    
}