package eapli.base.warehousemanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WidthTest {
    @Test
    public void ensureWidthIsCreated(){
        Width width = new Width(300L);
        assertNotNull(width);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWidthIsPositive(){
        Width width = new Width(0L);
    }
}
