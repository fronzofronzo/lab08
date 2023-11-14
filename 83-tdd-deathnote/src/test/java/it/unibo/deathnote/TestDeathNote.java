package it.unibo.deathnote;

import java.util.jar.Attributes.Name;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    final private static int NEGATIVE_NUM = -1;
    private static String NAME = "Gerry Scotti";
    private static String OTHER_NAME = "Giuseppe Lovato";

    private DeathNote testDeathNote = new DeathNoteImpl();

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
    public void testDeath() {
        Assertions.assertFalse(testDeathNote.isNameWritten(NAME));
        testDeathNote.writeName(NAME);
        Assertions.assertTrue(testDeathNote.isNameWritten(NAME));
        Assertions.assertFalse(testDeathNote.isNameWritten(OTHER_NAME));
        Assertions.assertFalse(testDeathNote.isNameWritten(""));
    }

    @Test
    public void checkCauseDeath() {
        final String cause = "Acc sparato";
        try {
            testDeathNote.writeDeathCause(cause);
            Assertions.fail();
        } catch (IllegalStateException e ) {
            Assertions.assertEquals("There's no name in this DeathNote", e.getMessage());
        }
        testDeathNote.writeName(NAME);
        Assertions.assertEquals(testDeathNote.getDeathCause(NAME), "heart attack");
        testDeathNote.writeName(OTHER_NAME);
        testDeathNote.writeDeathCause("karting accident");
    }
}