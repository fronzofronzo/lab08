package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.jar.Attributes.Name;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static it.unibo.deathnote.api.DeathNote.RULES;
import static java.lang.Thread.sleep;

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
        for(int i = 1; i< RULES.size(); i++) {
            Assertions.assertNotNull(testDeathNote.getRule(i));
            assertFalse(testDeathNote.getRule(i).isBlank());
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
    public void checkCauseDeath() throws InterruptedException {
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
        Assertions.assertTrue(testDeathNote.writeDeathCause("karting accident"));
        Assertions.assertEquals("karting accident", testDeathNote.getDeathCause(OTHER_NAME));
        Thread.sleep(100);
        Assertions.assertFalse(testDeathNote.writeDeathCause(cause));
        assertEquals("karting accident", testDeathNote.getDeathCause(OTHER_NAME));
    }

    @Test
    public void checkWritingDetails() throws InterruptedException {
        try {
            testDeathNote.writeDetails("it was an accident");
            fail();
        } catch ( IllegalStateException e) {
            assertEquals(e.getMessage(), "there is no name written in this DeathNote");
        }
        testDeathNote.writeName(NAME);
        assertEquals(testDeathNote.getDeathDetails(NAME), "");
        assertTrue(testDeathNote.writeDetails("ran for too long"));
        assertEquals(testDeathNote.getDeathDetails(NAME), "ran for too long");
        testDeathNote.writeName(OTHER_NAME);
        Thread.sleep(6100);
        assertFalse(testDeathNote.writeDetails("gone too far"));
        assertEquals("", testDeathNote.getDeathDetails(OTHER_NAME));
    }
}