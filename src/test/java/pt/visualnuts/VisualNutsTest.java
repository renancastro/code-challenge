package pt.visualnuts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisualNutsTest {

    @Test
    void number_1_should_return_visual() {
        assertEquals("1", VisualNuts.execute(1));
    }

    @Test
    void number_3_should_return_visual() {
        assertEquals("Visual", VisualNuts.execute(3));
    }

    @Test
    void number_5_should_return_nuts() {
        assertEquals("Nuts", VisualNuts.execute(5));
    }

    @Test
    void number_10_should_return_nuts() {
        assertEquals("Nuts", VisualNuts.execute(10));
    }

    @Test
    void number_15_should_return_visual_nuts() {
        assertEquals("VisualNuts", VisualNuts.execute(15));
    }

    @Test
    void number_30_should_return_visual_nuts() {
        assertEquals("VisualNuts", VisualNuts.execute(30));
    }

    @Test
    void number_0_should_return_visual_nuts() {
        assertEquals("VisualNuts", VisualNuts.execute(0));
    }

    @Test
    void number_7_should_return_7() {
        assertEquals("7", VisualNuts.execute(7));
    }

    @Test
    void number_minus_one_should_return_minus_one() {
        assertEquals("-1", VisualNuts.execute(-1));
    }

}